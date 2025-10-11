package com.tianyi.primeval.mixin;

import com.google.common.collect.ImmutableList;
import com.tianyi.primeval.entity.TheBreakSwordPlus;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public class EntityMixin
{
    @Shadow private ImmutableList<Entity> passengers;

    @Inject(method = "isVehicle",at =@At("HEAD"), cancellable = true, remap = true)
    private  void passTheBreakSword2(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(
                !this.passengers.stream().filter(et ->
                        !(et instanceof TheBreakSwordPlus)
                ).toList().isEmpty()
        );
    }
    @Inject(method = "canChangeDimensions",at =@At("HEAD"))
    private  void passTheBreakSword(CallbackInfoReturnable<Boolean> cir){
        var en =  ((Entity)(Object)this);
        if (en instanceof ServerPlayer entity){

            if (entity.level() instanceof ServerLevel serverLevel) {
                //theBreakSwordPlus.changeDimension(serverLevel.getServer().getLevel(event.getTo()));
                serverLevel.getEntities(entity, AABB.ofSize(entity.position(),10,10,10)
                ).forEach(e->{
                            if (e instanceof TheBreakSwordPlus a) {
                                if (a.getOwner() != null && a.getOwner().equals(entity)) {
                                    a.discard();
                                    entity.getPersistentData().putLong("Yuangaung_LastTrigger", 0);
                                }
                            }
                        }
                );
            }
        }
    }
}
