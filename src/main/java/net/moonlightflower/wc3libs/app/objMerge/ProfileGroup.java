package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Profile files the game reads object descriptions from, grouped by kind.
 * <p>
 * A custom object that leaves the object-modification file has to be
 * described in a profile file, as base objects are. Its base object's keys
 * are copied group by group into the group's {@link #getTarget() target},
 * so a skin key never lands among func/strings keys or the other way round:
 * which of these the game reads into one table is not documented, and
 * mirroring the base object's layout holds either way.
 * <p>
 * Targets are never localized files, since writing one would replace the
 * player's language with the bundled one for every object it holds. Files
 * are listed in the order later entries win on a duplicate key; the game's
 * own order is not documented, and the snapshot has 26 such keys, all display
 * strings.
 */
public enum ProfileGroup {
    UNIT_DATA("Units\\CampaignUnitFunc.txt", "Units\\CampaignUnitStrings.txt",
        "Units\\HumanUnitFunc.txt", "Units\\HumanUnitStrings.txt",
        "Units\\NeutralUnitFunc.txt", "Units\\NeutralUnitStrings.txt",
        "Units\\NightElfUnitFunc.txt", "Units\\NightElfUnitStrings.txt",
        "Units\\OrcUnitFunc.txt", "Units\\OrcUnitStrings.txt",
        "Units\\UndeadUnitFunc.txt", "Units\\UndeadUnitStrings.txt"),
    UNIT_SKIN("Units\\UnitSkin.txt", "Units\\UnitSkinStrings.txt"),
    UNIT_WEAPONS_FUNC("Units\\UnitWeaponsFunc.txt"),
    UNIT_WEAPONS_SKIN("Units\\UnitWeaponsSkin.txt"),
    UNIT_ADDONS("Units\\UnitAddons.txt"),

    ITEM_DATA("Units\\ItemFunc.txt", "Units\\ItemStrings.txt"),
    ITEM_SKIN("Units\\ItemSkin.txt", "Units\\ItemSkinStrings.txt"),
    ITEM_ADDONS("Units\\ItemAddons.txt"),

    ABILITY_DATA("Units\\CampaignAbilityFunc.txt", "Units\\CampaignAbilityStrings.txt",
        "Units\\CommonAbilityFunc.txt", "Units\\CommonAbilityStrings.txt",
        "Units\\HumanAbilityFunc.txt", "Units\\HumanAbilityStrings.txt",
        "Units\\NeutralAbilityFunc.txt", "Units\\NeutralAbilityStrings.txt",
        "Units\\NightElfAbilityFunc.txt", "Units\\NightElfAbilityStrings.txt",
        "Units\\OrcAbilityFunc.txt", "Units\\OrcAbilityStrings.txt",
        "Units\\UndeadAbilityFunc.txt", "Units\\UndeadAbilityStrings.txt",
        "Units\\ItemAbilityFunc.txt", "Units\\ItemAbilityStrings.txt"),
    ABILITY_SKIN("Units\\AbilitySkin.txt", "Units\\AbilitySkinStrings.txt"),
    ABILITY_STAT_MODIFIERS("Units\\AbilityStatModifiers.txt"),

    UPGRADE_DATA("Units\\CampaignUpgradeFunc.txt", "Units\\CampaignUpgradeStrings.txt",
        "Units\\HumanUpgradeFunc.txt", "Units\\HumanUpgradeStrings.txt",
        "Units\\NeutralUpgradeFunc.txt", "Units\\NeutralUpgradeStrings.txt",
        "Units\\NightElfUpgradeFunc.txt", "Units\\NightElfUpgradeStrings.txt",
        "Units\\OrcUpgradeFunc.txt", "Units\\OrcUpgradeStrings.txt",
        "Units\\UndeadUpgradeFunc.txt", "Units\\UndeadUpgradeStrings.txt"),
    UPGRADE_SKIN("Units\\UpgradeSkin.txt", "Units\\UpgradeSkinStrings.txt"),

    DESTRUCTABLE_SKIN("Units\\DestructableSkin.txt", "Units\\DestructableSkinStrings.txt"),
    DESTRUCTABLE_ADDONS("Units\\DestructableAddons.txt"),

    DOODAD_SKIN("Doodads\\DoodadSkins.txt", "Doodads\\DoodadSkinStrings.txt"),
    DOODAD_ADDONS("Doodads\\DoodadSkinsAddons.txt");

    private final List<String> _files;

    ProfileGroup(@Nonnull String... files) {
        _files = Collections.unmodifiableList(Arrays.asList(files));
    }

    /** @return the group's files, in the order later ones win */
    @Nonnull
    public List<String> getFiles() {
        return _files;
    }

    /** @return the non-localized file new sections of this group are written to */
    @Nonnull
    public String getTarget() {
        return _files.get(0);
    }
}
