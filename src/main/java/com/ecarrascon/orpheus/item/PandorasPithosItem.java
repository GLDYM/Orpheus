package com.ecarrascon.orpheus.item;

import com.ecarrascon.orpheus.registry.ItemsRegistry;
import com.ecarrascon.orpheus.util.PlayerUtils;
import com.ecarrascon.orpheus.util.WorldUtils;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.List;

public class PandorasPithosItem extends Item {
    public PandorasPithosItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide() && !pPlayer.isSpectator()) {
            PlayerUtils.decrementHeldItem(pPlayer, ItemsRegistry.PANDORAS_PITHOS.get());
            WorldUtils.summonLightning(pLevel, pPlayer);
            pPlayer.addEffect(getRandomEffect(pLevel));
            getRandomItem(pPlayer, pLevel);
            spawnRandomEntity(pPlayer, pLevel);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }


    private void spawnRandomEntity(Player player, Level world) {
        List<EntityType<?>> possibleEntities = Arrays.asList(
                EntityType.VINDICATOR,
                EntityType.PIG
        );

        EntityType<?> randomEntityType = possibleEntities.get(world.getRandom().nextInt(0, possibleEntities.size() - 1));

        int witherPossiblity = world.getRandom().nextInt(0, 199);

        if (witherPossiblity == 0) {
            randomEntityType = EntityType.WITHER; // 0.5% chance to spawn the Wither
        }

        int random = world.getRandom().nextInt(0, 7); // After randomly choosing the entity it will spawn 0 to 6 entities

        for (int i = 0; i < random; i++) {
            Entity randomEntity = randomEntityType.create(world);
            if (randomEntity != null) {
                randomEntity.setPos(player.position());
                world.addFreshEntity(randomEntity);
                if (witherPossiblity == 1 && i == 0) {
                    // Hi! If you are reading this, you are such a netrunner.
                    // Second, all this "cat" thing is just an Easter Egg, my cat is called Zeus and yeah, funny!
                    Cat zeus = getZeus(player, world);
                    world.addFreshEntity(zeus);
                }
            }
        }
    }
    private Cat getZeus(Player player, Level world) {
        Cat zeus = new Cat(EntityType.CAT, world);
        zeus.setCustomName(Component.nullToEmpty("Zeus"));
        zeus.setHealth(500f);
        zeus.setGlowingTag(true);
        zeus.setPos(player.position());
        return zeus;
    }
    private void getRandomItem(Player player, Level world) {
        List<ItemStack> possibleItems = Arrays.asList(
                Items.DIAMOND.getDefaultInstance(),
                ItemsRegistry.TEARS_OF_HADES.get().getDefaultInstance(),
                Items.BONE.getDefaultInstance(),
                Items.STICK.getDefaultInstance()
        );

        ItemStack randomItem = possibleItems.get(world.getRandom().nextInt(0, possibleItems.size() - 1));
        int random = world.getRandom().nextInt(0, 6);
        for (int i = 0; i < random; i++) {
            ItemStack itemCopy = randomItem.copy();
            if (!player.getInventory().add(itemCopy)) {
                Block.dropResources(world.getBlockState(player.blockPosition()), world, player.blockPosition(), null, null, itemCopy);
            }
        }

    }

    private MobEffectInstance getRandomEffect(Level pLevel) {
        List<Holder<MobEffect>> possibleEffects = Arrays.asList(
                MobEffects.MOVEMENT_SPEED,
                MobEffects.DAMAGE_BOOST,
                MobEffects.ABSORPTION,
                MobEffects.DAMAGE_RESISTANCE,
                MobEffects.REGENERATION,
                MobEffects.POISON,
                MobEffects.CONFUSION,
                MobEffects.BLINDNESS,
                MobEffects.WITHER,
                MobEffects.WEAKNESS
        );

        var randomEffect = possibleEffects.get(pLevel.getRandom().nextInt(0, possibleEffects.size() - 1));

        return new MobEffectInstance(randomEffect, 1800, 3);
    }

}
