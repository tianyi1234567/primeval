package com.tianyi.primeval.data;

import com.tianyi.origin.block.ModBlocks;
import com.tianyi.origin.item.ModItems;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

import static com.tianyi.primeval.data.SRelicBuiltInRegsitry.getEnchantmentID;

public class SrelicBladeRecipeProvioder extends RecipeProvider implements IConditionBuilder {
    public SrelicBladeRecipeProvioder(PackOutput output) {
        super(output);
    }

    protected void buildRecipes(Consumer<FinishedRecipe> consumer){
        //示例
        //SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.JINYUAN.location())
                //.pattern("ABA")
                //.pattern("CDE")
                //.pattern("ABA")
                //.define('B',  Items.NETHER_STAR)
                //.define('A',  SBItems.proudsoul_crystal)
                //.define('D',  SlashBladeIngredient.of(
                //        RequestDefinition.Builder.newInstance()
                //                .name(SRelicBuiltInRegsitry.BLADE.location())
                //                .killCount(9000)
                //                .refineCount(40)
                //                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT),1))
                //                .build()))
                //.define('C',Items.BLAZE_ROD)
                //.define('E',  Items.DIAMOND_BLOCK)
                //.unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.VOID.location())
        .pattern("CBC")
        .pattern("BAB")
        .pattern("CBC")
        .define('B',  Items.PAPER)
        .define('A',  SBItems.slashblade_wood)
        .define('C',  SBItems.proudsoul_tiny)
        .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.MOYING.location())
        .pattern("FBE")
        .pattern("ADA")
        .pattern("GBF")
        .define('B',  Items.COAL_BLOCK)
        .define('A',  Items.BLAZE_ROD)
        .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())//name后面的引用要正确！
                                .killCount(2000)
                                .proudSoul(3000)
                                .refineCount(10)
                                .build()))
        .define('G',ModItems.SHANYAO.get())
        .define('F',Items.BONE_MEAL)
        .define('E',ModItems.YEGUANG.get())
        .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.DANCE_LIGHT.location())
                .pattern("BCB")
                .pattern("ADA")
                .pattern("BCB")
                .define('B',  ModItems.GUANG.get())
                .define('A',  Items.EXPERIENCE_BOTTLE)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(500)
                                .proudSoul(1500)
                                .refineCount(5)
                                .build()))
                .define('C',ModItems.SHANYAO.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.SUKURA.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BAB")
                .define('B',  ModBlocks.YINGK.get())
                .define('A',  ModItems.YINGHUA.get())
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(50)
                                .refineCount(1)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.WIND.location())
                .pattern("FBF")
                .pattern("ADA")
                .pattern("FBF")
                .define('B',  SBItems.slashblade)
                .define('A',  ModItems.TIANKONG.get())
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(1000)
                                .proudSoul(3000)
                                .refineCount(5)
                                .build()))
                .define('F',Items.DIAMOND)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.STRONG_WIND.location())
                .pattern("FBF")
                .pattern("ADA")
                .pattern("FGF")
                .define('B',  SBItems.slashblade)
                .define('A',  Items.DIAMOND_BLOCK)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.WIND.location())
                                .killCount(5500)
                                .proudSoul(6000)
                                .refineCount(20)
                                .build()))
                .define('F',ModItems.TIANKONG.get())
                .define('G',Items.ELYTRA)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YANG.location())
                .pattern("FFF")
                .pattern("ADA")
                .pattern("BGB")
                .define('F',  ModItems.YANG.get())
                .define('A',  Items.ENCHANTED_GOLDEN_APPLE)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(500)
                                .proudSoul(1000)
                                .refineCount(10)
                                .build()))
                .define('B',Items.GOLD_INGOT)
                .define('G',Items.GOLD_BLOCK)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YIN.location())
                .pattern("FAF")
                .pattern("CDC")
                .pattern("BBB")
                .define('F',SBItems.proudsoul_sphere)
                .define('A',  Items.NETHERITE_INGOT)
                .define('C',  Items.ANCIENT_DEBRIS)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(500)
                                .proudSoul(1000)
                                .refineCount(10)
                                .build()))
                .define('B',  ModItems.YIN.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.FALLING_STAR.location())
                .pattern("BAB")
                .pattern("HDN")
                .pattern("BFB")
                .define('B',  Items.LAPIS_LAZULI)
                .define('A',  Items.EMERALD_BLOCK)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(2550)
                                .proudSoul(6000)
                                .refineCount(30)
                                .build()))
                .define('H',Items.GOLD_BLOCK)
                .define('N',Items.DIAMOND_BLOCK)
                .define('F',  ModItems.XINGZHEN.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.XUESE.location())
                .pattern("BAB")
                .pattern("BDB")
                .pattern("FFF")
                .define('B',  Items.REDSTONE_BLOCK)
                .define('A',  ModItems.KUWEI.get())
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(4444)
                                .proudSoul(10000)
                                .refineCount(15)
                                .build()))
                .define('F',  ModItems.XIEJIED.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.CAIJUE.location())
                .pattern("FAF")
                .pattern("BDB")
                .pattern("FCF")
                .define('B',  Items.DIAMOND_SWORD)
                .define('A',  Items.HEART_OF_THE_SEA)
                .define('D',  Items.CAKE)
                .define('F',Items.GLASS_BOTTLE)
                .define('C',  Items.WATER_BUCKET)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.JIXU.location())
                .pattern(" A ")
                .pattern("FBF")
                .pattern(" D ")
                .define('B',  ModItems.YINYANG.get())
                .define('A',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YIN.location())
                                .killCount(2000)
                                .proudSoul(10000)
                                .refineCount(10)
                                .build()))
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YANG.location())
                                .killCount(2000)
                                .proudSoul(10000)
                                .refineCount(10)
                                .build()))
                .define('F',Items.NETHER_STAR)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YINYANG.location())
                .pattern("CNF")
                .pattern("A D")
                .pattern("ENC")
                .define('E',  ModItems.YANG.get())
                .define('N',  ModItems.YINYANG.get())
                .define('C',  Items.NETHERITE_INGOT)
                .define('A',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YIN.location())
                                .killCount(2550)
                                .proudSoul(10000)
                                .refineCount(45)
                                .build()))
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YANG.location())
                                .killCount(2550)
                                .proudSoul(10000)
                                .refineCount(45)
                                .build()))
                .define('F',  ModItems.YIN.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.MIRACLE.location())
                .pattern("BEB")
                .pattern("CDH")
                .pattern("FAF")
                .define('B',  ModItems.XUANCAID.get())
                .define('A',  Items.DRAGON_EGG)
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.LIEYAN.location())//name后面的引用要正确！
                                .killCount(3500)
                                .proudSoul(35000)
                                .refineCount(20)
                                .build()))
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.WEATERK.location())//name后面的引用要正确！
                                .killCount(1)
                                .proudSoul(200000)
                                .refineCount(20)
                                .build()))
                .define('H',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.DANCE_LIGHT.location())//name后面的引用要正确！
                                .killCount(3500)
                                .proudSoul(35000)
                                .refineCount(20)
                                .build()))
                .define('F',Items.NETHER_STAR)
                .define('E',  ModItems.QIJI.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.SHIYUAN.location())
                .pattern("BCB")
                .pattern("CDC")
                .pattern("FCF")
                .define('B',  ModItems.EXPRO.get())
                .define('F',  Items.NETHER_STAR)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FALLING_STAR.location())
                                .killCount(3500)
                                .proudSoul(6000)
                                .refineCount(10)
                                .build()))
                .define('C',ModItems.XINGZHEN.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.SHIYUANEX.location())
                .pattern("BCB")
                .pattern("CDC")
                .pattern("BCB")
                .define('B',  ModItems.XINGZHEN.get())
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.SHIYUAN.location())
                                .killCount(6500)
                                .proudSoul(10000)
                                .refineCount(20)
                                .build()))
                .define('C',ModItems.XUANCAID.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

            SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.REVE.location())
                .pattern(" A ")
                .pattern("BDC")
                .pattern(" F ")
                .define('A',  Items.IRON_HELMET)
                .define('B',  Items.IRON_LEGGINGS)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(10)
                                .proudSoul(700)
                                .refineCount(1)
                                .build()))
                .define('C',  Items.IRON_BOOTS)
                .define('F',  Items.IRON_CHESTPLATE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ZHENSHI.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BAB")
                .define('A',  Items.SPIDER_EYE)
                .define('B',  Items.BRICK)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(100)
                                .proudSoul(1000)
                                .refineCount(7)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.LIFA.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BAB")
                .define('A',  Items.SLIME_BALL)
                .define('B',  Items.ENDER_PEARL)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(100)
                                .proudSoul(1000)
                                .refineCount(7)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YANYU.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BAB")
                .define('A',  Items.AMETHYST_SHARD)
                .define('B',  Items.PAPER)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(100)
                                .proudSoul(1000)
                                .refineCount(7)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.JIAZHI.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BAB")
                .define('A',  Items.DIAMOND)
                .define('B',  Items.PRISMARINE_SHARD)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(100)
                                .proudSoul(1000)
                                .refineCount(7)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.GUIZE.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BAB")
                .define('A',  Items.EMERALD)
                .define('B',  Items.STICK)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(100)
                                .proudSoul(1000)
                                .refineCount(7)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YIYI.location())
                .pattern("BAB")
                .pattern("BDB")
                .pattern("BBB")
                .define('A',  Items.HEART_OF_THE_SEA)
                .define('B',  Items.SNOWBALL)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(100)
                                .proudSoul(1000)
                                .refineCount(7)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ZUNYAN.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BAB")
                .define('A',  Items.BLAZE_ROD)
                .define('B',  Items.BONE)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(100)
                                .proudSoul(1000)
                                .refineCount(7)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.DAY.location())
                .pattern("CDE")
                .pattern("BHF")
                .pattern("AVG")
                .define('A',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.ZHENSHI.location())
                                .killCount(250)
                                .proudSoul(2000)
                                .refineCount(7)
                                .build()))
                .define('B',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.LIFA.location())
                                .killCount(250)
                                .proudSoul(2000)
                                .refineCount(7)
                                .build()))
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YANYU.location())
                                .killCount(250)
                                .proudSoul(2000)
                                .refineCount(7)
                                .build()))
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.JIAZHI.location())
                                .killCount(250)
                                .proudSoul(2000)
                                .refineCount(7)
                                .build()))
                .define('E',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.GUIZE.location())
                                .killCount(250)
                                .proudSoul(2000)
                                .refineCount(7)
                                .build()))
                .define('F',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YIYI.location())
                                .killCount(250)
                                .proudSoul(2000)
                                .refineCount(7)
                                .build()))
                .define('G',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.ZUNYAN.location())
                                .killCount(250)
                                .proudSoul(2000)
                                .refineCount(7)
                                .build()))
                .define('V',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.REVE.location())
                                .killCount(7)
                                .proudSoul(7000)
                                .refineCount(7)
                                .build()))
                .define('H',  Items.DRAGON_EGG)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.LIEYAN.location())
                .pattern("BAB")
                .pattern("ADA")
                .pattern("BCB")
                .define('A',  Items.BLAZE_ROD)
                .define('B',  Items.BLAZE_POWDER)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(2000)
                                .proudSoul(5000)
                                .refineCount(15)
                                .build()))
                .define('C',  ModItems.XIEJIED.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ZHEJIAN.location())
                .pattern(" B ")
                .pattern("ADC")
                .pattern(" B ")
                .define('A',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YIN.location())
                                .killCount(2000)
                                .proudSoul(1000)
                                .refineCount(10)
                                .build()))
                .define('B',  Items.NETHERITE_SCRAP)
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YANG.location())
                                .killCount(2000)
                                .proudSoul(1000)
                                .refineCount(10)
                                .build()))
                .define('D',  Items.STICK)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.WEATERK.location())
                .pattern("DBD")
                .pattern("ACA")
                .pattern("BDB")
                .define('A',  SBItems.proudsoul_crystal)
                .define('B',  Items.WATER_BUCKET)
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(2000)
                                .proudSoul(40000)
                                .refineCount(1)
                                .build()))
                .define('D',  Items.MUSIC_DISC_13)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.HONGY.location())
                .pattern("FBF")
                .pattern("ACA")
                .pattern("BDB")
                .define('F',  Items.REDSTONE_BLOCK)
                .define('B',  ModItems.XIEJIED.get())
                .define('A',  ModItems.WITHEREDL.get())
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.SUKURA.location())
                                .killCount(800)
                                .proudSoul(4000)
                                .refineCount(10)
                                .build()))
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.XUESE.location())
                                .killCount(6000)
                                .proudSoul(40000)
                                .refineCount(40)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.XUANCAI.location())
                .pattern("ABC")
                .pattern("DIE")
                .pattern("FGH")
                .define('A',  Items.WHITE_WOOL)
                .define('B',  Items.RED_WOOL)
                .define('C',  Items.YELLOW_WOOL)
                .define('D',  Items.GREEN_WOOL)
                .define('E',  Items.BLACK_WOOL)
                .define('F',  Items.BLUE_WOOL)
                .define('G',  Items.PURPLE_WOOL)
                .define('H',  Items.PINK_WOOL)
                .define('I',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.MOYING.location())
                                .killCount(5000)
                                .proudSoul(50000)
                                .refineCount(20)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.BAILOUEX.location())
                .pattern("FFF")
                .pattern("ACA")
                .pattern("FDF")
                .define('F',  SBItems.proudsoul_trapezohedron)
                .define('A',  ModItems.XUANCAID.get())
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BAILOU.location())
                                .killCount(4000)
                                .proudSoul(20000)
                                .refineCount(10)
                                .build()))
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(1)
                                .proudSoul(1)
                                .refineCount(25)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.LOUGUANEX.location())
                .pattern("FFF")
                .pattern("ACA")
                .pattern("FDF")
                .define('F',  SBItems.proudsoul_trapezohedron)
                .define('A',  ModItems.XUANCAID.get())
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.LOUGUAN.location())
                                .killCount(4000)
                                .proudSoul(20000)
                                .refineCount(10)
                                .build()))
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(1)
                                .proudSoul(1)
                                .refineCount(25)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.VOIDEX.location())
                .pattern("CBC")
                .pattern("BAB")
                .pattern("CBC")
                .define('B',  Items.FIRE_CHARGE)
                .define('A',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .refineCount(25)
                                .build()))
                .define('C',  SBItems.proudsoul_tiny)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.JINAN.location())
                .pattern("CBC")
                .pattern("BAB")
                .pattern("DED")
                .define('B',  ModItems.KUWEI.get())
                .define('E',  ModItems.WITHEREDL.get())
                .define('A',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .killCount(1500)
                                .refineCount(50)
                                .build()))
                .define('C',  Items.DRAGON_BREATH)
                    .define('D',  Items.BLAZE_POWDER)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.HUANGWU.location())
                .pattern("ADB")
                .pattern("CFC")
                .pattern("BGA")
                .define('A',  ModItems.XIEJIED.get())
                .define('B',  ModItems.WITHERED.get())
                .define('C',  Items.NETHER_STAR)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .build()))
                .define('F',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.JINAN.location())
                                .killCount(3000)
                                .refineCount(80)
                                .build()))
                .define('G',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.JIXU.location())
                                .killCount(4000)
                                .refineCount(20)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.PYQG.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',  ModItems.XIEJIED.get())
                .define('B',  Items.GRASS_BLOCK)
                .define('C',  ModItems.TIANKONG.get())
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.MIRACLE.location())
                                .killCount(10000)
                                .refineCount(200)
                                .build()))
                .define('E',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.VOID.location())
                                .refineCount(100)
                                .build()))
                .define('F',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.HUANGWU.location())
                                .killCount(10000)
                                .refineCount(200)
                                .build()))
                .define('G',  ModItems.MOYINGD.get())
                .define('H',  Items.WRITABLE_BOOK)
                .define('I',  ModItems.SHANYAO.get())
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);


        oreSmelting(consumer,
                List.of(Items.YELLOW_WOOL), // 包装成Ingredient
                RecipeCategory.MISC,
                getItem(SRelicBuiltInRegsitry.VOID.location()),
                0.7f,
                200,
                "primeval_void");
        }


    public Item getItem(ResourceLocation item) {
        return ForgeRegistries.ITEMS.getValue(item);
    }
}
