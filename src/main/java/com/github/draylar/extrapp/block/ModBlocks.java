package com.github.draylar.extrapp.block;

import com.github.draylar.extrapp.block.BasePressurePlateBlock;
import com.github.draylar.extrapp.utils.PressurePlateRenderType;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {

    public static final Block OBSIDIAN = new BasePressurePlateBlock.Builder()
            .withCollisionValidator(PlayerEntity.class::isInstance)
            .buildAndRegister("obsidian");

    public static final Block GLASS = new BasePressurePlateBlock.Builder()
            .withSettings(FabricBlockSettings.of(Material.GLASS).hardness(.5f).build())
            .withRenderType(PressurePlateRenderType.GLASS)
            .registerInvisibleVariant(false)
            .buildAndRegister("glass");

    public static final Block SLIME = new BasePressurePlateBlock.Builder()
            .withSettings(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).sounds(BlockSoundGroup.SLIME).hardness(.3f).build())
            .withRenderType(PressurePlateRenderType.SLIME)
            .buildAndRegister("slime");

    public static final Block CACTUS = new BasePressurePlateBlock.Builder()
            .withSettings(FabricBlockSettings.of(Material.CACTUS).hardness(.5f).build())
            .onCollision((state, world, pos, entity) -> {
                if (entity instanceof HostileEntity) {
                    entity.damage(DamageSource.CACTUS, 4);
                }
            })
            .buildAndRegister("cactus");

    public static final Block FIERY = new BasePressurePlateBlock.Builder()
            .onCollision((state, world, pos, entity) -> {
                if (entity instanceof HostileEntity) {
                    entity.setOnFireFor(5);
                }
            })
            .buildAndRegister("fiery");

    public static final Block OCEAN = new BasePressurePlateBlock.Builder()
            .withHardness(.7f)
            .onCollision((state, world, pos, entity) -> {
                if (entity instanceof LivingEntity) {
                    entity.extinguish();
                    ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 20 * 15, 2));
                }
            })
            .buildAndRegister("ocean");

    public static void init() {
        // NO-OP
    }

    private ModBlocks() {
        // NO-OP
    }
}
