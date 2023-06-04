package net.xetanth87.campaignsplitter;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoopCallAdjustments {
	public static class AdjustmentDetails
	{
		private List<Integer> parameterIndices;
		public boolean isBool, isLocal;
		public Integer pointIndex = null;

		public AdjustmentDetails(List<Integer> parameterIndices)
		{
			this.parameterIndices = parameterIndices;
		}

		public List<Integer> getParameterIndices() {
			return parameterIndices;
		}

		public AdjustmentDetails setBool()
		{
			isBool = true;
			return this;
		}

		public AdjustmentDetails setLocal()
		{
			isLocal = true;
			return this;
		}

		// if we set isLocal, we don't need to save the point since the function is called only once
		public AdjustmentDetails withPoint(int i)
		{
			pointIndex = i;
			return this;
		}
	}

	static Map<String, AdjustmentDetails> callParameters = new HashMap<>();

	private static AdjustmentDetails addCallParameters(String functionName, List<Integer> list)
	{
		AdjustmentDetails ad = new AdjustmentDetails(list);
		callParameters.put(functionName, ad);
		return ad;
	}

	private static AdjustmentDetails addCallParameters(String functionName, Integer i)
	{
		List<Integer> list = Collections.singletonList(i);
		return addCallParameters(functionName, list);
	}

	static {
		addCallParameters("SetPlayerTeam", 0);
		addCallParameters("SetPlayerStartLocation", 0);
		addCallParameters("ForcePlayerStartLocation", 0);
		addCallParameters("SetPlayerAlliance", Arrays.asList(0, 1));
		addCallParameters("SetPlayerRacePreference", 0);
		addCallParameters("SetPlayerController", 0);
		addCallParameters("ForceAddPlayer", 1);
		addCallParameters("ForceRemovePlayer", 1);
//		addCallParameters("TriggerRegisterPlayerAllianceChange", Arrays.asList(0, 1));
//		addCallParameters("TriggerRegisterPlayerStateEvent", 0);
		addCallParameters("TriggerRegisterPlayerChatEvent", 1);
		addCallParameters("IsUnitSelected", 0).setBool();
		addCallParameters("UnitShareVision", 1);
		addCallParameters("SetPlayerState", 0);
		addCallParameters("RemovePlayer", 0);
		addCallParameters("SetFogStateRect", 0);
		addCallParameters("SetFogStateRadius", 0);
		addCallParameters("SetFogStateRadiusLoc", 0).withPoint(2);
		addCallParameters("CreateFogModifierRect", 0);
		addCallParameters("CreateFogModifierRadius", 0);
		addCallParameters("CreateFogModifierRadiusLoc", 0).withPoint(2);
		addCallParameters("DisplayTextToPlayer", 0);
		addCallParameters("DisplayTimedTextToPlayer", 0);
		//skipping leaderboard stuff
		//skipping AI stuff
		//skipping request/sync stuff
		addCallParameters("CameraSetupApplyForPlayer", 2).setLocal();
		addCallParameters("CameraSetupApplyForPlayerSmooth", 2).setLocal();
		addCallParameters("SetCameraFieldForPlayer", 0).setLocal();
		addCallParameters("SetCameraTargetControllerNoZForPlayer", 0).setLocal();
		addCallParameters("SetCameraPositionForPlayer", 0).setLocal();
		addCallParameters("SetCameraPositionLocForPlayer", 0).setLocal();
		addCallParameters("RotateCameraAroundLocBJ", 2).setLocal();
		addCallParameters("PanCameraToForPlayer", 0).setLocal();
		addCallParameters("PanCameraToLocForPlayer", 0).setLocal();
		addCallParameters("PanCameraToTimedForPlayer", 0).setLocal();
		addCallParameters("PanCameraToTimedLocForPlayer", 0).setLocal();
		addCallParameters("PanCameraToTimedLocWithZForPlayer", 0).setLocal();
		addCallParameters("SmartCameraPanBJ", 0).setLocal();
		addCallParameters("SetCinematicCameraForPlayer", 0).setLocal();
		addCallParameters("ResetToGameCameraForPlayer", 0).setLocal();
		addCallParameters("CameraSetSourceNoiseForPlayer", 0).setLocal();
		addCallParameters("CameraSetTargetNoiseForPlayer", 0).setLocal();
		addCallParameters("CameraSetEQNoiseForPlayer", 0).setLocal();
		addCallParameters("CameraClearNoiseForPlayer", 0).setLocal();
		addCallParameters("SetCameraBoundsToRectForPlayerBJ", 0).setLocal();
		addCallParameters("AdjustCameraBoundsForPlayerBJ", 1).setLocal();
		addCallParameters("SetCameraQuickPositionForPlayer", 0).setLocal();
		addCallParameters("SetCameraQuickPositionLocForPlayer", 0).setLocal();
		addCallParameters("StopCameraForPlayerBJ", 0).setLocal();
		addCallParameters("SetCameraOrientControllerForPlayerBJ", 0).setLocal();
		addCallParameters("TriggerRegisterPlayerSelectionEventBJ", 1);
		addCallParameters("TriggerRegisterPlayerKeyEventBJ", 1);
		addCallParameters("TriggerRegisterPlayerMouseEventBJ", 1);
		addCallParameters("TriggerRegisterPlayerEventLeave", 1);
		addCallParameters("TriggerRegisterPlayerEventEndCinematic", 1);
		addCallParameters("CreateFogModifierRectSimple", 0);
		addCallParameters("CreateFogModifierRadiusLocSimple", 0).withPoint(2);
		addCallParameters("CreateFogModifierRectBJ", 1);
		addCallParameters("CreateFogModifierRadiusLocBJ", 1).withPoint(3);
		addCallParameters("StartSoundForPlayerBJ", 0).setLocal();
		addCallParameters("VolumeGroupSetVolumeForPlayerBJ", 0).setLocal();
		addCallParameters("ClearSelectionForPlayer", 0).setLocal();
		addCallParameters("SelectUnitForPlayerSingle", 1).setLocal();
		addCallParameters("SelectGroupForPlayerBJ", 1).setLocal();
		addCallParameters("SelectUnitAddForPlayer", 1).setLocal();
		addCallParameters("SelectUnitRemoveForPlayer", 1).setLocal();
		addCallParameters("UnitShareVisionBJ", 2);
		addCallParameters("ForceAddPlayerSimple", 0);
		addCallParameters("ForceRemovePlayerSimple", 0);
		addCallParameters("EnumUnitsSelected", 0);
		addCallParameters("SetPlayerAllianceBJ", Arrays.asList(0, 3));
		addCallParameters("SetPlayerAllianceStateAllyBJ", Arrays.asList(0, 1));
		addCallParameters("SetPlayerAllianceStateVisionBJ", Arrays.asList(0, 1));
		addCallParameters("SetPlayerAllianceStateControlBJ", Arrays.asList(0, 1));
		addCallParameters("SetPlayerAllianceStateFullControlBJ", Arrays.asList(0, 1));
		addCallParameters("SetPlayerAllianceStateBJ", Arrays.asList(0, 1));
		addCallParameters("MeleeVictoryDialogBJ", 0);
		addCallParameters("MeleeDefeatDialogBJ", 0);
		addCallParameters("GameOverDialogBJ", 0);
		addCallParameters("RemovePlayerPreserveUnitsBJ", 0);
		addCallParameters("CustomVictoryDialogBJ", 0);
		addCallParameters("CustomVictorySkipBJ", 0).setLocal();
		addCallParameters("CustomVictoryBJ", 0);
		addCallParameters("CustomDefeatDialogBJ", 0);
		addCallParameters("CustomDefeatBJ", 0);
		addCallParameters("TimerDialogDisplayForPlayerBJ", 2);
		addCallParameters("PingMinimapForPlayer", 0).setLocal();
		addCallParameters("PingMinimapLocForPlayer", 0).setLocal();
	}
}
