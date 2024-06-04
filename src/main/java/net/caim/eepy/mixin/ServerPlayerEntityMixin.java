package net.caim.eepy.mixin;


import com.mojang.authlib.GameProfile;
import net.caim.eepy.EepyMod;
import net.caim.eepy.interfaces.ServerPlayerEntityWithAFK;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements ServerPlayerEntityWithAFK {

    @Shadow public abstract long getLastActionTime();

    @Override
    public boolean isAfk(){

        return afk;
    }

    @Unique
    private boolean afk;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }
    @Inject(method = "playerTick", at = @At("TAIL"))
    private void eepy$authKickTimer(CallbackInfo ci){

        EepyMod.LOGGER.info("{}",this.getLastActionTime());
    }


}
