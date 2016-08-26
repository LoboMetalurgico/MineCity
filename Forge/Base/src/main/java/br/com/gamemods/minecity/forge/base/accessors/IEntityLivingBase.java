package br.com.gamemods.minecity.forge.base.accessors;

import br.com.gamemods.minecity.forge.base.Referenced;
import br.com.gamemods.minecity.forge.base.core.transformer.forge.EntityLivingBaseTransformer;
import net.minecraft.entity.EntityLivingBase;

@Referenced(at = EntityLivingBaseTransformer.class)
public interface IEntityLivingBase extends IEntity
{
    @Override
    default EntityLivingBase getForgeEntity()
    {
        return (EntityLivingBase) this;
    }

    default boolean isElytraFlying()
    {
        return getForgeEntity().isElytraFlying();
    }
}
