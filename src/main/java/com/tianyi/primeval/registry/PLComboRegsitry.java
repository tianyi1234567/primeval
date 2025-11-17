package com.tianyi.primeval.registry;

import com.tianyi.primeval.Primeval;
import com.tianyi.primeval.cover.ExSlashEffect;
import com.tianyi.primeval.cover.SlashEffect;
import com.tianyi.primeval.entity.SuperdriveEntity;
import com.tianyi.primeval.entity.xeper.ExperDriveEntity;
import com.tianyi.primeval.mclib.Utils.SlashBlade.SlashEffectUtils;
import com.tianyi.primeval.specialattacks.Drive.Blowing;
import com.tianyi.primeval.specialattacks.Drive.Experdrive;
import com.tianyi.primeval.specialattacks.Drive.SuperDV;
import com.tianyi.primeval.specialattacks.swrod.*;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.event.handler.FallHandler;
import mods.flammpfeil.slashblade.init.DefaultResources;
import mods.flammpfeil.slashblade.registry.combo.ComboState;
import mods.flammpfeil.slashblade.slasharts.*;
import mods.flammpfeil.slashblade.util.AttackManager;
import com.tianyi.primeval.specialattacks.*;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.tianyi.primeval.Primeval.MODID;

public class PLComboRegsitry {
    public static final DeferredRegister<ComboState> COMBO_STATES =
            DeferredRegister.create(ComboState.REGISTRY_KEY, MODID);

    public static final RegistryObject<ComboState> ALL_REUSE = COMBO_STATES.register//收刀动作，不写的话放完SA会闪退
            (
                    "all_reuse",
                    ComboState.Builder.newInstance().startAndEnd(459, 488).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation).next(entity -> SlashBlade.prefix("none"))
                            .nextOfTimeout(entity -> SlashBlade.prefix("none"))
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder().put(0, AttackManager::playQuickSheathSoundAction).build())
                            .releaseAction(ComboState::releaseActionQuickCharge)::build
            );
    public static final RegistryObject<ComboState> BIGDRIVE_VERTICAL = COMBO_STATES.register//注册名
            (
                    "big_drive",//名字
                    ComboState.Builder.newInstance().startAndEnd(2100, 2200).priority(75)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1.5F))//调用
                                    .put(3, (entityIn) -> Blowing.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 3, 1.5f))
                                    .put(4, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 141F, Vec3.ZERO, false, false, 1.5F))
                                    .put(5, (entityIn) -> Blowing.doSlash(entityIn, 141F, 20, Vec3.ZERO, false, 3, 1.5f))
                                    .put(6, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 192F, Vec3.ZERO, false, false, 1.5F))
                                    .put(7, (entityIn) -> Blowing.doSlash(entityIn, 192F, 20, Vec3.ZERO, false, 3, 1.5f))
                                    .put(8, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 243F, Vec3.ZERO, false, false, 1.5F))
                                    .put(9, (entityIn) -> Blowing.doSlash(entityIn, 243F, 20, Vec3.ZERO, false, 4, 1.5f))
                                    .put(10, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 294F, Vec3.ZERO, false, false, 1.5F))
                                    .put(11, (entityIn) -> Blowing.doSlash(entityIn, 294F, 20, Vec3.ZERO, false, 4, 1.5f))
                                    .put(12, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 345F, Vec3.ZERO, false, false, 1.5F))
                                    .put(13, (entityIn) -> Blowing.doSlash(entityIn, 345F, 20, Vec3.ZERO, false, 4, 1.5f))
                                    .put(14, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 36F, Vec3.ZERO, false, false, 1.5F))
                                    .put(26, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90F, Vec3.ZERO, false, false, 1F))
                                    .put(27, (entityIn) -> Blowing.doSlash(entityIn, -90F, 20, Vec3.ZERO, false, 8, 1.5f))
                                    .put(28, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 0F, Vec3.ZERO, false, false, 1F))
                                    .put(29, (entityIn) -> Blowing.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 8, 1.5f))
                                    .put(30, (entityIn) -> BigDriveSummon.doSlash(entityIn, 0F, 10, Vec3.ZERO, false, 1, 2f)).build())

                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> WIND_IS_VLOWING = COMBO_STATES.register
            (
                    "wind_is_blowing",
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 1.5F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90))
                                    .put(8, (entityIn) -> SwordX.doSlash(entityIn, false, 50.0, 1F))
                                    .put(9, (entityIn) -> SwordX.doSlash(entityIn, false, 50.0, 1F))
                                    .put(10, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(11, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(12, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(13, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90))
                                    .put(15, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 1.5F, new Vec3(0.0, 0.0, 1.0))).build())

                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> XUE_YU = COMBO_STATES.register//注册名
            (
                    "xueyu",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, JudgementCut::doJudgementCut)
                                    .put(2, JudgementCut::doJudgementCut)
                                    .put(3, JudgementCut::doJudgementCut)
                                    .put(4, JudgementCut::doJudgementCut)
                                    .put(5, (entityIn) -> SwordX.doSlash(entityIn, false, 25.0, 1F))
                                    .put(10, (entityIn) -> SwordX.doSlash(entityIn, false, 18.0, 1F))
                                    .put(15, (entityIn) -> SwordX.doSlash(entityIn, false, 25.0, 1F))
                                    .put(20, (entityIn) -> SwordX.doSlash(entityIn, false, 18.0, 1F)).build())

                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> HUANYING_GAI = COMBO_STATES.register//注册名
            (
                    "huanyinggai",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 0.5F))//调用
                                    .put(2, (entityIn) -> Drive.doSlash(entityIn, 90F, 15, Vec3.ZERO, false, 2, 2f))
                                    .put(3, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 141F, Vec3.ZERO, false, false, 0.5F))
                                    .put(4, (entityIn) -> Drive.doSlash(entityIn, 141F, 15, Vec3.ZERO, false, 2, 2f))
                                    .put(5, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 192F, Vec3.ZERO, false, false, 0.5F))
                                    .put(6, (entityIn) -> Drive.doSlash(entityIn, 192F, 15, Vec3.ZERO, false, 2, 2f))
                                    .put(7, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 243F, Vec3.ZERO, false, false, 0.5F))
                                    .put(8, (entityIn) -> Drive.doSlash(entityIn, 243F, 15, Vec3.ZERO, false, 2, 2f))
                                    .put(9, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 294F, Vec3.ZERO, false, false, 0.5F))
                                    .put(11, (entityIn) -> Drive.doSlash(entityIn, 294F, 15, Vec3.ZERO, false, 2, 2f))
                                    .put(12, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 345F, Vec3.ZERO, false, false, 0.5F))
                                    .put(13, (entityIn) -> Drive.doSlash(entityIn, 345F, 15, Vec3.ZERO, false, 2, 2f))
                                    .put(14, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 36F, Vec3.ZERO, false, false, 0.5F))
                                    .put(15, (entityIn) -> Drive.doSlash(entityIn, 36F, 15, Vec3.ZERO, false, 2, 2f)).build())

                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> ERTIAN = COMBO_STATES.register//注册名
            (
                    "ertian",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(40, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 2))//调用
                                    .put(3, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -115F, Vec3.ZERO, false, false, 1))
                                    .put(5, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 141F, Vec3.ZERO, false, false, 2))
                                    .put(7, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -166F, Vec3.ZERO, false, false, 1))
                                    .put(9, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 192F, Vec3.ZERO, false, false, 2))
                                    .put(11, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -217F, Vec3.ZERO, false, false, 1))
                                    .put(13, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 243F, Vec3.ZERO, false, false, 2))
                                    .put(15, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -263F, Vec3.ZERO, false, false, 1))
                                    .put(17, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 294F, Vec3.ZERO, false, false, 2))
                                    .put(19, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -319F, Vec3.ZERO, false, false, 1))
                                    .put(21, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 345F, Vec3.ZERO, false, false, 2))
                                    .put(23, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -10F, Vec3.ZERO, false, false, 1))
                                    .put(25, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 36F, Vec3.ZERO, false, false, 2))
                                    .put(27, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -61F, Vec3.ZERO, false, false, 1))
                                    .put(28, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(29, (entityIn) -> XXsword.doXXsword(entityIn, false, 25, 1f)).build())

                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> LIANZHAN = COMBO_STATES.register//注册名
            (
                    "lianzhan",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(75)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1.2F))//调用
                                    .put(3, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 141F, Vec3.ZERO, false, false, 1.2F))
                                    .put(5, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 192F, Vec3.ZERO, false, false, 1.2F))
                                    .put(7, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 243F, Vec3.ZERO, false, false, 1.2F))
                                    .put(9, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 294F, Vec3.ZERO, false, false, 1.2F))
                                    .put(12, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 345F, Vec3.ZERO, false, false, 1.2F))
                                    .put(14, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 36F, Vec3.ZERO, false, false, 1.2F)).build())

                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> LIANZHANB = COMBO_STATES.register//注册名
            (
                    "lianzhanb",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 0.75))
                                    .put(2, (entityIn) -> Drive.doSlash(entityIn, 90F, 20, Vec3.ZERO, false, 1, 2f))
                                    .put(3, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 325F, Vec3.ZERO, false, false, 0.75))
                                    .put(4, (entityIn) -> Drive.doSlash(entityIn, 325F, 20, Vec3.ZERO, false, 2, 2f))
                                    .put(5, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 235F, Vec3.ZERO, false, false, 0.75))
                                    .put(6, (entityIn) -> Drive.doSlash(entityIn, 235F, 20, Vec3.ZERO, false, 1, 2f)).build())

                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> SILIE = COMBO_STATES.register//注册名
            (
                    "silie",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 180F, Vec3.ZERO, false, false, 1.5F))
                                    .put(2, (entityIn) -> DiverSP.doSlash(entityIn, false, 15, 2, 20))
                                    .put(3, JudgementCut::doJudgementCut)
                                    .put(4, (entityIn) -> Drive.doSlash(entityIn, 180F, 8, Vec3.ZERO, false, 10, 3f)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> SILIEEX = COMBO_STATES.register//注册名
            (
                    "silieex",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(15, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> DiverSP.doSlash(entityIn, false, 20, 2, 20))
                                    .put(2, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 180F, Vec3.ZERO, false, false, 4))
                                    .put(3, JudgementCut::doJudgementCut)
                                    .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(5, (entityIn) -> Drive.doSlash(entityIn, 180F, 8, Vec3.ZERO, false, 6, 3f))
                                    .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(8, (entityIn) -> Drive.doSlash(entityIn, 180F, 8, Vec3.ZERO, false, 6, 3f))
                                    .put(9, JudgementCut::doJudgementCut)
                                    .put(11, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90))
                                    .put(13, (entityIn) -> YingYu.doSlash(entityIn, false, 15.0, 3F)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> YINHUAL = COMBO_STATES.register//注册名
            (
                    "yinghual",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 0.15))
                                    .put(2, (entityIn) -> YingYu.doSlash(entityIn, false, 3.0, 4F))
                                    .put(5, (entityIn) -> YingYu.doSlash(entityIn, false, 4.0, 4F))
                                    .put(9, (entityIn) -> YingYu.doSlash(entityIn, false, 4.0, 4F)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> JIXIAN = COMBO_STATES.register//注册名
            (
                    "jixian",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(20, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 190F, Vec3.ZERO, false, false, 0.15))
                                    .put(2, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 1))
                                    .put(4, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 0.5))
                                    .put(6, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 1))
                                    .put(8, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 0.5))
                                    .put(10, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 1))
                                    .put(12, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 0.5))
                                    .put(14, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 1))
                                    .put(16, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 0.5))
                                    .put(18, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, -90.0F + 180.0F * entityIn.getRandom().nextFloat(), AttackManager.genRushOffset(entityIn), false, false, 2)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> SHUXIAN = COMBO_STATES.register//注册名
            (
                    "shuixian",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(3, (entityIn) -> Drive.doSlash(entityIn, 90F, 10, Vec3.ZERO, false, 6, 2f))
                                    .put(4, (entityIn) -> Drive.doSlash(entityIn, 90F, 10, Vec3.ZERO, false, 6, 2f))
                                    .put(5, (entityIn) -> Drive.doSlash(entityIn, 90F, 10, Vec3.ZERO, false, 6, 2f)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> YUMAO = COMBO_STATES.register//注册名
            (
                    "yumao",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 180F, Vec3.ZERO, false, false, 1))
                                    .put(3, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(4, (entityIn) -> Drive.doSlash(entityIn, 180F, 15, Vec3.ZERO, false, 2, 3f))
                                    .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(6, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(7, (entityIn) -> Drive.doSlash(entityIn, 180F, 15, Vec3.ZERO, false, 2, 3f))
                                    .put(8, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(9, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(10, (entityIn) -> Drive.doSlash(entityIn, 180F, 15, Vec3.ZERO, false, 2, 3f))
                                    .put(11, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(12, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90))
                                    .put(13, (entityIn) -> Drive.doSlash(entityIn, 180F, 15, Vec3.ZERO, false, 2, 3f))
                                    .put(14, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, -90)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> TAICHU = COMBO_STATES.register//注册名
            (
                    "taichu",//名字
                    ComboState.Builder.newInstance().startAndEnd(2400, 2500).priority(100)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(70, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> XiWang.doSlash(entityIn, false, 6, 2f))
                                    .put(2, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 145F, Vec3.ZERO, false, false, 2))
                                    .put(3, (entityIn) -> BigDriveSummon.doSlash(entityIn, 145F, 10, Vec3.ZERO, false, 2, 2f))
                                    .put(10, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 145F, Vec3.ZERO, false, false, 1))
                                    .put(18, (entityIn) -> XiWang.doSlash(entityIn, false, 6, 2f))
                                    .put(19, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 2))
                                    .put(20, (entityIn) -> BigDriveSummon.doSlash(entityIn, 45F, 10, Vec3.ZERO, false, 2, 2f))
                                    .put(27, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 45F, Vec3.ZERO, false, false, 1))
                                    .put(35, (entityIn) -> XiWang.doSlash(entityIn, false, 6, 2f))
                                    .put(36, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 180F, Vec3.ZERO, false, false, 2))
                                    .put(37, (entityIn) -> BigDriveSummon.doSlash(entityIn, 180F, 10, Vec3.ZERO, false, 2, 2f))
                                    .put(40, (entityIn) -> XXswordSOM.doXXsword(entityIn, false, 30, 2))
                                    .put(45, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(46, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(50, (entityIn) -> XXswordSOM.doXXsword(entityIn, false, 25, 2))
                                    .put(60, (entityIn) -> XXswordSOM.doXXsword(entityIn, false, 25, 2)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> GUANGLAN = COMBO_STATES.register//注册名
            (
                    "guanglan",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(50)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(20, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(2, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(3, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(4, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(6, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 270))
                                    .put(8, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(9, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(10, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(11, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(12, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(13, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 270))
                                    .put(14, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(15, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(16, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(17, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(18, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(19, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 270))
                                    .put(20, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(21, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(22, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 90))
                                    .put(23, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(24, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(25, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 270)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> DAJIAN = COMBO_STATES.register//注册名
            (
                    "dajian",//名字
                    ComboState.Builder.newInstance().startAndEnd(2400, 2500).priority(100)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(240, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> DiverSW.doSlash(entityIn, false, 85, 1f, 20)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> XUEREN = COMBO_STATES.register//注册名
            (
                    "xueren",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(20, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> AttackManager.doSlash(entityIn, 145F, Vec3.ZERO, false, false, SlashEffectUtils.addslashbladesaDamageKey(entityIn)))
                                    .put(2, (entityIn) -> Drive.doSlash(entityIn, 145F, 15, Vec3.ZERO, false, 6, 3f))
                                    .put(8, (entityIn) -> AttackManager.doSlash(entityIn, 45F, Vec3.ZERO, false, false, SlashEffectUtils.addslashbladesaDamageKey(entityIn)))
                                    .put(9, (entityIn) -> Drive.doSlash(entityIn, 45F, 15, Vec3.ZERO, false, 7, 3f))
                                    .put(15, (entityIn) -> AttackManager.doSlash(entityIn, 90F, Vec3.ZERO, false, false, SlashEffectUtils.addslashbladesaDamageKey(entityIn)))
                                    .put(16, (entityIn) -> Drive.doSlash(entityIn, 90F, 15, Vec3.ZERO, false, 8, 3f))
                                    .put(17, (entityIn) -> YingYu.doSlash(entityIn, false, 40.0, 4F))
                                    .put(18, (entityIn) -> YingYu.doSlash(entityIn, false, 40.0, 4F)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> DYFS = COMBO_STATES.register//注册名
            (
                    "dyfs",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(20, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> DiverSP.doSlash(entityIn, false, 20, 2, 20)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> XUANDRIVE = COMBO_STATES.register//注册名
            (
                    "xuandrive",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 180F, Vec3.ZERO, false, false, 1))
                                    .put(2, (entityIn) -> Drive.doSlash(entityIn, 180F, 13, Vec3.ZERO, false, 2, 1f))
                                    .put(3, (entityIn) -> Drive.doSlash(entityIn, 90F, 13, Vec3.ZERO, false, 2, 1f))
                                    .put(4, (entityIn) -> Drive.doSlash(entityIn, 145F, 13, Vec3.ZERO, false, 2, 1f))
                                    .put(5, (entityIn) -> Drive.doSlash(entityIn, 45F, 13, Vec3.ZERO, false, 3, 1f))
                                    .put(6, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 0F, Vec3.ZERO, false, false, 1))
                                    .put(7, (entityIn) -> Drive.doSlash(entityIn, 180F, 13, Vec3.ZERO, false, 3, 1f))
                                    .put(8, (entityIn) -> Drive.doSlash(entityIn, 90F, 13, Vec3.ZERO, false, 3, 1f))
                                    .put(9, (entityIn) -> Drive.doSlash(entityIn, 145F, 13, Vec3.ZERO, false, 4, 1f))
                                    .put(10, (entityIn) -> Drive.doSlash(entityIn, 45F, 13, Vec3.ZERO, false, 4, 1f)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> CI_JI = COMBO_STATES.register//注册名
            (
                    "ciji",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(5)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("piercing")))
                            .nextOfTimeout(entity -> Primeval.prefix("piercing_end"))//收刀或下一步动作
                            .nextOfTimeout(entity -> Primeval.prefix("piercing_end2"))//收刀或下一步动作
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 90F, Vec3.ZERO, false, false, 1))
                                    .put(2, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.35F : 2.5F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(3, (entityIn) -> AttackManager.areaAttack(entityIn, KnockBacks.toss.action, 2F, true, false, true)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> QIXING = COMBO_STATES.register//注册名
            (
                    "qixing",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(3, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(6, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(9, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(11, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> QIXINGEX = COMBO_STATES.register//注册名
            (
                    "qixingex",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀或下一步动作
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(3, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(5, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(6, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(7, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(9, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(11, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(12, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(13, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(15, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(17, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180))
                                    .put(18, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(19, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 0))
                                    .put(21, (entityIn) -> entityIn.moveRelative(entityIn.isInWater() ? 0.25F : 2F, new Vec3(0.0, 0.0, 1.0)))
                                    .put(23, (entityIn) -> CircleSlash.doCircleSlashAttack(entityIn, 180)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> DPRO = COMBO_STATES.register//注册名
            (
                    "dpro",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> XXsword.doXXsword(entityIn, false, 10, 1f))
                                    .put(5, (entityIn) -> XXsword.doXXsword(entityIn, false, 10, 1f))
                                    .put(10, (entityIn) -> XXsword.doXXsword(entityIn, false, 15, 1f))
                                    .put(12, (entityIn) -> SwordRain.doSlash(entityIn, false, 15, 1f))
                                    .put(15, (entityIn) -> XXsword.doXXsword(entityIn, false, 20, 1f)).build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> BREAK_SKY = COMBO_STATES.register("break_sky",
            ComboState.Builder.newInstance()
                    .startAndEnd(2200, 2255)
                    .priority(50)
                    .next((entity) -> SlashBlade.prefix("none"))
                    .nextOfTimeout((entity) -> SlashBlade.prefix("none"))
                    .motionLoc(DefaultResources.ExMotionLocation)
                    .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                            .put(1, (entityIn) -> {
                                if (entityIn instanceof Player player) {
                                    // 在服务端发送消息
                                    if (!player.level().isClientSide()) {
                                        // 定义消息数组
                                        String[] messages = {
                                                "§4此剑，§e当斩！",
                                                "§f化作，§e尘土吧！！！",
                                                "§b始源之力，§d为我所用！",
                                                "§c斩断一切，§6归于虚无！",
                                                "§9星辰陨落，§5万物寂灭！",
                                                "§a乾坤逆转，§c日月无光！"
                                        };
                                        // 随机选择一条消息发送
                                        int randomIndex = player.getRandom().nextInt(messages.length);
                                        player.sendSystemMessage(Component.literal(messages[randomIndex]));
                                    }
                                }
                            })
                            .put(12, (entityIn) -> SlashEffect.SakuraEnd.doSlash(entityIn, 145F, Vec3.ZERO, false, false, 1F))
                            .put(13, (entityIn) -> SuperDV.doSlash(entityIn, 145F, 120, Vec3.ZERO, false, 3, 0.01f))
                            .put(15, (entityIn) -> {
                                // 播放烈焰人被攻击的音效（因为我懒得注册新的音效了）
                                entityIn.level().playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(),
                                        net.minecraft.sounds.SoundEvents.BLAZE_HURT,
                                        net.minecraft.sounds.SoundSource.PLAYERS,
                                        1.0F, 1.0F);})
                            .build())
                    .addTickAction(FallHandler::fallDecrease)
                    .addHitEffect(StunManager::setStun)
                    ::build
    );
    public static final RegistryObject<ComboState> PXKKL = COMBO_STATES.register//注册名
            (
                    "pxkkl",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(3, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(5, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(7, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(9, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(11, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(13, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(15, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(17, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(19, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(21, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(23, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(25, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .put(27, (entityIn) -> Experdrive.doSlash(entityIn,0F, 60.0F, 2.0F, 2156543, 1.0F))
                                    .build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
    public static final RegistryObject<ComboState> SANZHZHU = COMBO_STATES.register//注册名
            (
                    "sanzhzhu",//名字
                    ComboState.Builder.newInstance().startAndEnd(1600, 1659).priority(40)
                            .motionLoc(DefaultResources.ExMotionLocation)
                            .next(ComboState.TimeoutNext.buildFromFrame(10, entity -> SlashBlade.prefix("none")))
                            .nextOfTimeout(entity -> Primeval.prefix("all_reuse"))//收刀
                            .addTickAction(ComboState.TimeLineTickAction.getBuilder()
                                    .put(1, (entityIn) -> SanZhu.doSlash(entityIn, false, 4, 1f))
                                    .put(25, (entityIn) -> {
                                        // 播放音效
                                        entityIn.level().playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(),
                                                net.minecraft.sounds.SoundEvents.BLAZE_HURT,
                                                net.minecraft.sounds.SoundSource.PLAYERS,
                                                1.0F, 1.0F);})
                                    .build())
                            .addHitEffect(StunManager::setStun)
                            ::build
            );
}

