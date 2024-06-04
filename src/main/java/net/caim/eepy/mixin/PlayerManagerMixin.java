package net.caim.eepy.mixin;

import net.caim.eepy.interfaces.ServerPlayerEntityWithAFK;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin{



    @Inject(method = "remove", at = @At(value = "TAIL"))
    private void eepy$removeUnauntenticated(ServerPlayerEntity player, CallbackInfo ci){
        ((ServerPlayerEntityWithAFK) player).isAfk();
    }
}
