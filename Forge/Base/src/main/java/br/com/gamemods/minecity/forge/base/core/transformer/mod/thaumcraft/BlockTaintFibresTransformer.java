package br.com.gamemods.minecity.forge.base.core.transformer.mod.thaumcraft;

import br.com.gamemods.minecity.api.CollectionUtil;
import br.com.gamemods.minecity.forge.base.core.MethodPatcher;
import br.com.gamemods.minecity.forge.base.core.Referenced;
import br.com.gamemods.minecity.forge.base.core.transformer.BasicTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;
import org.objectweb.asm.tree.*;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.objectweb.asm.Opcodes.*;
import static org.objectweb.asm.Opcodes.RETURN;

@Referenced
@MethodPatcher
public class BlockTaintFibresTransformer extends BasicTransformer
{
    @Referenced("br.com.gamemods.minecity.forge.mc_1_7_10.core.MineCitySevenCoreMod")
    public BlockTaintFibresTransformer()
    {
        super("thaumcraft.common.blocks.BlockTaintFibres");
    }

    @Override
    protected void patch(String name, ClassNode node, ClassReader reader)
    {
        String self = name.replace('.','/');

        MethodNode setBiomeAt = new MethodNode(ACC_PUBLIC|ACC_STATIC, "mineCity$setBiomeAt", "(Lnet/minecraft/world/World;IILnet/minecraft/world/biome/BiomeGenBase;III)V", null, null);
        setBiomeAt.visitCode();
        setBiomeAt.visitVarInsn(ALOAD, 0);
        setBiomeAt.visitVarInsn(ILOAD, 1);
        setBiomeAt.visitVarInsn(ILOAD, 2);
        setBiomeAt.visitVarInsn(ILOAD, 4);
        setBiomeAt.visitVarInsn(ILOAD, 5);
        setBiomeAt.visitVarInsn(ILOAD, 6);
        setBiomeAt.visitMethodInsn(INVOKESTATIC,
                "br.com.gamemods.minecity.forge.base.protection.thaumcraft.ThaumHooks".replace('.','/'),
                "onBlockChangeBiome",
                "(Lnet/minecraft/world/World;IIIII)Z",
                false
        );
        Label elseLabel = new Label();
        setBiomeAt.visitJumpInsn(IFEQ, elseLabel);
        setBiomeAt.visitInsn(RETURN);
        setBiomeAt.visitLabel(elseLabel);
        setBiomeAt.visitVarInsn(ALOAD, 0);
        setBiomeAt.visitVarInsn(ILOAD, 1);
        setBiomeAt.visitVarInsn(ILOAD, 2);
        setBiomeAt.visitVarInsn(ALOAD, 3);
        setBiomeAt.visitMethodInsn(INVOKESTATIC,
                "thaumcraft.common.lib.utils.Utils".replace('.','/'),
                "setBiomeAt",
                "(Lnet/minecraft/world/World;IILnet/minecraft/world/biome/BiomeGenBase;)V",
                false
        );
        setBiomeAt.visitInsn(RETURN);
        setBiomeAt.visitEnd();

        MethodNode spreadFibres = new MethodNode(ACC_PUBLIC|ACC_STATIC, "mineCity$spreadFibres", "(Lnet/minecraft/world/World;IIIIII)Z", null, null);
        spreadFibres.visitCode();
        spreadFibres.visitVarInsn(ALOAD, 0);
        spreadFibres.visitVarInsn(ILOAD, 1);
        spreadFibres.visitVarInsn(ILOAD, 2);
        spreadFibres.visitVarInsn(ILOAD, 3);
        spreadFibres.visitVarInsn(ILOAD, 4);
        spreadFibres.visitVarInsn(ILOAD, 5);
        spreadFibres.visitVarInsn(ILOAD, 6);
        spreadFibres.visitMethodInsn(INVOKESTATIC,
                "br.com.gamemods.minecity.forge.base.protection.thaumcraft.ThaumHooks".replace('.','/'),
                "onBlockChangeOther",
                "(Lnet/minecraft/world/World;IIIIII)Z",
                false
        );
        elseLabel = new Label();
        spreadFibres.visitJumpInsn(IFEQ, elseLabel);
        spreadFibres.visitInsn(ICONST_0);
        spreadFibres.visitInsn(IRETURN);
        spreadFibres.visitLabel(elseLabel);
        spreadFibres.visitVarInsn(ALOAD, 0);
        spreadFibres.visitVarInsn(ILOAD, 1);
        spreadFibres.visitVarInsn(ILOAD, 2);
        spreadFibres.visitVarInsn(ILOAD, 3);
        spreadFibres.visitMethodInsn(INVOKESTATIC,
                name.replace('.','/'),
                "spreadFibres",
                "(Lnet/minecraft/world/World;III)Z",
                false
        );
        spreadFibres.visitInsn(IRETURN);
        spreadFibres.visitEnd();

        AtomicReference<MethodNode> setBlock = new AtomicReference<>();

        for(MethodNode method : node.methods)
        {
            switch(method.name)
            {
                case "taintBiomeSpread":
                    CollectionUtil.stream(method.instructions.iterator())
                            .filter(ins -> ins.getOpcode() == INVOKESTATIC).map(MethodInsnNode.class::cast)
                            .filter(ins -> ins.owner.equals("thaumcraft/common/lib/utils/Utils"))
                            .filter(ins -> ins.desc.equals("(Lnet/minecraft/world/World;IILnet/minecraft/world/biome/BiomeGenBase;)V"))
                            .filter(ins -> ins.name.equals("setBiomeAt"))
                            .anyMatch(ins ->
                            {
                                InsnList list = new InsnList();
                                list.add(new VarInsnNode(ILOAD, 1));
                                list.add(new VarInsnNode(ILOAD, 2));
                                list.add(new VarInsnNode(ILOAD, 3));
                                method.instructions.insertBefore(ins, list);
                                ins.itf = false;
                                ins.owner = self;
                                ins.name = setBiomeAt.name;
                                ins.desc = setBiomeAt.desc;
                                return true;
                            });
                    break;
                default:
                    if(method.desc.equals("(Lnet/minecraft/world/World;IIILjava/util/Random;)V"))
                    {
                        /*
                        CollectionUtil.stream(method.instructions.iterator())
                                .filter(ins -> ins.getOpcode() == INVOKEVIRTUAL).map(MethodInsnNode.class::cast)
                                .filter(ins -> ins.owner.equals("net/minecraft/world/World"))
                                .filter(ins -> ins.desc.equals("(IIILnet/minecraft/block/Block;II)Z"))
                                .map(ins-> method.instructions.indexOf(ins)).sorted(Comparator.reverseOrder())
                                .map(i-> (MethodInsnNode) method.instructions.get(i))
                                .forEachOrdered(ins -> {
                                    MethodNode wrapper = setBlock.get();
                                    if(wrapper == null)
                                    {
                                        setBlock.set(wrapper = new MethodNode(ACC_PUBLIC|ACC_STATIC,
                                                "mineCity$setBlock",
                                                "(Lnet/minecraft/world/World;IIILnet/minecraft/block/Block;IIIII)Z",
                                                null, null
                                        ));
                                        wrapper.visitCode();
                                        wrapper.visitVarInsn(ALOAD, 0);
                                        wrapper.visitVarInsn(ILOAD, 1);
                                        wrapper.visitVarInsn(ILOAD, 2);
                                        wrapper.visitVarInsn(ILOAD, 3);
                                        wrapper.visitVarInsn(ILOAD, 7);
                                        wrapper.visitVarInsn(ILOAD, 8);
                                        wrapper.visitVarInsn(ILOAD, 9);
                                        wrapper.visitMethodInsn(INVOKESTATIC,
                                                "br.com.gamemods.minecity.forge.base.protection.thaumcraft.ThaumHooks".replace('.','/'),
                                                "onBlockChangeOther",
                                                "(Lnet/minecraft/world/World;IIIIII)Z",
                                                false
                                        );
                                        Label label = new Label();
                                        wrapper.visitJumpInsn(IFEQ, label);
                                        wrapper.visitInsn(ICONST_0);
                                        wrapper.visitInsn(IRETURN);
                                        wrapper.visitLabel(label);
                                        wrapper.visitVarInsn(ALOAD, 0);
                                        wrapper.visitVarInsn(ILOAD, 1);
                                        wrapper.visitVarInsn(ILOAD, 2);
                                        wrapper.visitVarInsn(ILOAD, 3);
                                        wrapper.visitVarInsn(ALOAD, 4);
                                        wrapper.visitVarInsn(ILOAD, 5);
                                        wrapper.visitVarInsn(ILOAD, 6);
                                        wrapper.visitMethodInsn(ins.getOpcode(), ins.owner, ins.name, ins.desc, ins.itf);
                                        wrapper.visitInsn(IRETURN);
                                        wrapper.visitEnd();
                                    }

                                    InsnList list = new InsnList();
                                    list.add(new VarInsnNode(ILOAD, 2));
                                    list.add(new VarInsnNode(ILOAD, 3));
                                    list.add(new VarInsnNode(ILOAD, 4));
                                    method.instructions.insertBefore(ins, list);
                                    ins.setOpcode(INVOKESTATIC);
                                    ins.itf = false;
                                    ins.owner = name.replace('.','/');
                                    ins.name = wrapper.name;
                                    ins.desc = wrapper.desc;
                                });
                        */

                        CollectionUtil.stream(method.instructions.iterator())
                                .filter(ins -> ins.getOpcode() == INVOKESTATIC).map(MethodInsnNode.class::cast)
                                .filter(ins -> ins.owner.equals("thaumcraft/common/blocks/BlockTaintFibres"))
                                .filter(ins -> ins.desc.equals("(Lnet/minecraft/world/World;III)Z"))
                                .filter(ins -> ins.name.equals("spreadFibres"))
                                .anyMatch(ins ->
                                {
                                    InsnList list = new InsnList();
                                    list.add(new VarInsnNode(ILOAD, 2));
                                    list.add(new VarInsnNode(ILOAD, 3));
                                    list.add(new VarInsnNode(ILOAD, 4));
                                    method.instructions.insertBefore(ins, list);
                                    ins.name = spreadFibres.name;
                                    ins.desc = spreadFibres.desc;
                                    return true;
                                });
                    }
            }
        }

        node.methods.add(setBiomeAt);
        node.methods.add(spreadFibres);
        //node.methods.add(Objects.requireNonNull(setBlock.get(), "mineCity$setBlock was not generated"));
    }
}
