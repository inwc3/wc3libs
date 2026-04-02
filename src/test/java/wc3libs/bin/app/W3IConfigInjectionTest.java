package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.MapFlag;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.dataTypes.app.Controller;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.port.GameVersion;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class W3IConfigInjectionTest {
    private static W3I.Player createPlayer(int num, String name, W3I.Player.UnitRace race, float x, float y, int... allyHighPlayers) {
        W3I.Player player = new W3I.Player();

        player.setNum(num);
        player.setName(name);
        player.setType(Controller.USER);
        player.setRace(race);
        player.setStartPosFixed(1);
        player.setStartPos(new Coords2DF(x, y));
        player.addAllyHighPrioPlayerNums(allyHighPlayers);

        return player;
    }

    private static W3I createSparsePlayersW3I() {
        W3I w3i = new W3I();

        w3i.setMapName("mapName");
        w3i.setMapDescription("description");
        w3i.setFlag(MapFlag.FIXED_PLAYER_FORCE_SETTING, true);

        w3i.addPlayer(createPlayer(0, "Player 1", W3I.Player.UnitRace.NIGHT_ELF, -4352F, 128F, 1, 2));
        w3i.addPlayer(createPlayer(1, "Player 2", W3I.Player.UnitRace.NIGHT_ELF, -4352F, 0F, 0, 2));
        w3i.addPlayer(createPlayer(2, "Player 3", W3I.Player.UnitRace.NIGHT_ELF, -4352F, -128F, 0, 1));
        w3i.addPlayer(createPlayer(6, "Player 7", W3I.Player.UnitRace.UNDEAD, 4352F, 128F, 7, 8));
        w3i.addPlayer(createPlayer(7, "Player 8", W3I.Player.UnitRace.UNDEAD, 4352F, 0F, 6, 8));
        w3i.addPlayer(createPlayer(8, "Player 9", W3I.Player.UnitRace.UNDEAD, 4352F, -128F, 6, 7));

        W3I.Force westernForce = new W3I.Force();
        westernForce.setName("Western Force");
        westernForce.addPlayerNums(0, 1, 2);
        westernForce.setFlag(W3I.Force.Flags.Flag.ALLIED, true);
        westernForce.setFlag(W3I.Force.Flags.Flag.ALLIED_VICTORY, true);
        westernForce.setFlag(W3I.Force.Flags.Flag.SHARED_VISION, true);
        w3i.addForce(westernForce);

        W3I.Force easternForce = new W3I.Force();
        easternForce.setName("Eastern Force");
        easternForce.addPlayerNums(6, 7, 8);
        easternForce.setFlag(W3I.Force.Flags.Flag.ALLIED, true);
        easternForce.setFlag(W3I.Force.Flags.Flag.ALLIED_VICTORY, true);
        easternForce.setFlag(W3I.Force.Flags.Flag.SHARED_VISION, true);
        w3i.addForce(easternForce);

        return w3i;
    }

    private static int indexOfOrFail(String text, String expected) {
        int index = text.indexOf(expected);
        assertTrue(index >= 0, "Missing expected snippet: " + expected + "\n\nActual output:\n" + text);
        return index;
    }

    private static String compact(String text) {
        return text.replaceAll("\\s+", "");
    }

    @Test
    public void injectConfigsInJassUsesSequentialStartLocationsAndWeLikeOrder() throws Exception {
        W3I w3i = createSparsePlayersW3I();
        StringWriter sw = new StringWriter();

        w3i.injectConfigsInJassScript(
                new ByteArrayInputStream(("function config takes nothing returns nothing\n" +
                        "call SetTeams(99)\n" +
                        "endfunction\n").getBytes(StandardCharsets.UTF_8)),
                sw,
                GameVersion.VERSION_1_32
        );

        String output = compact(sw.toString());

        assertTrue(output.contains("callSetTeams(6)"));
        assertFalse(output.contains("callSetTeams(2)"));

        assertTrue(output.contains("callDefineStartLocation(0,-4352.0,128.0)"));
        assertTrue(output.contains("callDefineStartLocation(3,4352.0,128.0)"));
        assertTrue(output.contains("callDefineStartLocation(4,4352.0,0.0)"));
        assertTrue(output.contains("callDefineStartLocation(5,4352.0,-128.0)"));
        assertFalse(output.contains("callDefineStartLocation(6,4352.0,128.0)"));

        assertTrue(output.contains("callSetPlayerStartLocation(Player(6),3)"));
        assertTrue(output.contains("callForcePlayerStartLocation(Player(7),4)"));
        assertTrue(output.contains("callForcePlayerStartLocation(Player(8),5)"));

        assertTrue(output.contains("callSetPlayerState(Player(0),PLAYER_STATE_ALLIED_VICTORY,1)"));
        assertTrue(output.contains("callSetPlayerState(Player(8),PLAYER_STATE_ALLIED_VICTORY,1)"));
        assertTrue(output.contains("callSetPlayerAllianceStateVisionBJ(Player(0),Player(1),true)"));
        assertTrue(output.contains("callSetPlayerAllianceStateVisionBJ(Player(6),Player(7),true)"));

        int teamIndex = indexOfOrFail(output, "callSetPlayerTeam(Player(0),0)");
        int victoryIndex = indexOfOrFail(output, "callSetPlayerState(Player(0),PLAYER_STATE_ALLIED_VICTORY,1)");
        int allyIndex = indexOfOrFail(output, "callSetPlayerAllianceStateAllyBJ(Player(0),Player(1),true)");
        int visionIndex = indexOfOrFail(output, "callSetPlayerAllianceStateVisionBJ(Player(0),Player(1),true)");
        assertTrue(teamIndex < victoryIndex && victoryIndex < allyIndex && allyIndex < visionIndex);

        assertTrue(output.contains("callSetStartLocPrioCount(3,2)"));
        assertTrue(output.contains("callSetStartLocPrio(3,0,4,MAP_LOC_PRIO_HIGH)"));
        assertTrue(output.contains("callSetStartLocPrio(3,1,5,MAP_LOC_PRIO_HIGH)"));
        assertTrue(output.contains("callSetEnemyStartLocPrioCount(3,0)"));
        assertFalse(output.contains("callSetStartLocPrio(6,3,7,MAP_LOC_PRIO_HIGH)"));
    }

    @Test
    public void injectConfigsInLuaReplacesNamedConfigFunctions() throws Exception {
        W3I w3i = createSparsePlayersW3I();
        StringWriter sw = new StringWriter();
        String oldLuaScript = String.join("\n",
                "function config()",
                "    SetTeams(99)",
                "end",
                "",
                "function InitCustomTeams()",
                "    SetTeams(77)",
                "end",
                "",
                "function untouched()",
                "    return 1",
                "end");

        w3i.injectConfigsInLuaScript(new ByteArrayInputStream(oldLuaScript.getBytes(StandardCharsets.UTF_8)), sw);

        String output = compact(sw.toString());

        assertTrue(output.contains("functionuntouched()"));
        assertFalse(output.contains("SetTeams(99)"));
        assertFalse(output.contains("SetTeams(77)"));
        assertTrue(output.contains("SetTeams(6)"));
        assertTrue(output.contains("DefineStartLocation(3,4352.0,128.0)"));
        assertTrue(output.contains("SetPlayerStartLocation(Player(6),3)"));
        assertTrue(output.contains("SetPlayerState(Player(6),PLAYER_STATE_ALLIED_VICTORY,1)"));
        assertTrue(output.contains("SetPlayerAllianceStateVisionBJ(Player(6),Player(7),true)"));
    }

    @Test
    public void initCustomTeamsSupportsSharedControlFlags() throws Exception {
        W3I w3i = new W3I();

        w3i.addPlayer(createPlayer(0, "Player 1", W3I.Player.UnitRace.HUMAN, 0F, 0F, 1));
        w3i.addPlayer(createPlayer(1, "Player 2", W3I.Player.UnitRace.ORC, 128F, 0F, 0));

        W3I.Force force = new W3I.Force();
        force.addPlayerNums(0, 1);
        force.setFlag(W3I.Force.Flags.Flag.SHARED_UNIT_CONTROL, true);
        force.setFlag(W3I.Force.Flags.Flag.SHARED_UNIT_CONTROL_ADVANCED, true);
        w3i.addForce(force);

        StringWriter sw = new StringWriter();
        w3i.makeInitCustomTeams(false).write(sw, false);

        String output = compact(sw.toString());

        assertTrue(output.contains("callSetPlayerAllianceStateControlBJ(Player(0),Player(1),true)"));
        assertTrue(output.contains("callSetPlayerAllianceStateControlBJ(Player(1),Player(0),true)"));
        assertTrue(output.contains("callSetPlayerAllianceStateFullControlBJ(Player(0),Player(1),true)"));
        assertTrue(output.contains("callSetPlayerAllianceStateFullControlBJ(Player(1),Player(0),true)"));
    }
}
