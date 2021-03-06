/*
 * This file is part of SpoutPlugin (http://www.spout.org/).
 *
 * SpoutPlugin is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SpoutPlugin is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.getspout.spout.block.mcblock;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import gnu.trove.map.hash.TIntIntHashMap;

import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockMinecartTrack;
import net.minecraft.server.Entity;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.EntityPlayer;
import net.minecraft.server.IBlockAccess;
import net.minecraft.server.MovingObjectPosition;
import net.minecraft.server.Vec3D;
import net.minecraft.server.World;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import org.getspout.spout.block.SpoutCraftChunk;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spoutapi.SpoutManager;

public class CustomMinecartTrack extends BlockMinecartTrack implements CustomMCBlock {
	protected BlockMinecartTrack parent;

	protected CustomMinecartTrack(BlockMinecartTrack parent) {
		super(parent.id, parent.textureId, parent.i());
		this.parent = parent;

		updateField(parent, this, "strength");
		updateField(parent, this, "durability");
		updateField(parent, this, "bR");
		updateField(parent, this, "bS");
		updateField(parent, this, "bT");
		this.minX = parent.minX;
		this.minY = parent.minY;
		this.minZ = parent.minZ;
		this.maxX = parent.maxX;
		this.maxY = parent.maxY;
		this.maxZ = parent.maxZ;
		this.stepSound = parent.stepSound;
		this.cc = parent.cc;
		this.frictionFactor = parent.frictionFactor;
		updateField(parent, this, "name");
	}

	@Override
	public Block getParent() {
		return parent;
	}

	@Override
	public void setHardness(float hardness) {
		this.strength = hardness;
		updateField(this, parent, "strength");
	}

	public float getExplosionResistance() {
		return this.durability;
	}

	public void setExplosionResistance(float resistance) {
		this.durability = resistance;
	}

	@Override
	protected void k() {
		try {
			Method k = Block.class.getDeclaredMethod("k", (Class[]) null);
			k.setAccessible(true);
			k.invoke(parent, (Object[]) null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean b() {
		return parent.b();
	}

	@Override
	public float m() {
		return parent.m();
	}

	@Override
	public Block c(float f) {
		return super.c(f);
	}

	@Override
	public void a(float f, float f1, float f2, float f3, float f4, float f5) {
		if (parent != null) {
			parent.a(f, f1, f2, f3, f4, f5);
		} else {
			super.a(f, f1, f2, f3, f4, f5);
		}
	}

	@Override
	public boolean b(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return parent.b(iblockaccess, i, j, k, l);
	}

	@Override
	public int a(int i, int j) {
		return parent.a(i, j);
	}

	@Override
	public int a(int i) {
		return parent.a(i);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
		parent.a(world, i, j, k, axisalignedbb, arraylist);
	}

	@Override
	public AxisAlignedBB e(World world, int i, int j, int k) {
		return parent.e(world, i, j, k);
	}

	@Override
	public boolean a() {
		if (parent != null) {
			return parent.a();
		}
		return super.a();
	}

	@Override
	public boolean a(int i, boolean flag) {
		return parent.a(i, flag);
	}

	@Override
	public boolean E_() {
		return parent.E_();
	}

	@Override
	public void a(World world, int i, int j, int k, Random random) {
		parent.a(world, i, j, k, random);
	}

	@Override
	public void postBreak(World world, int i, int j, int k, int l) {
		parent.postBreak(world, i, j, k, l);
	}

	@Override
	public void doPhysics(World world, int i, int j, int k, int l) {
		parent.doPhysics(world, i, j, k, l);
	}

	@Override
	public int c() {
		return parent.c();
	}

	@Override
	public void onPlace(World world, int i, int j, int k) {
		parent.onPlace(world, i, j, k);
	}

	@Override
	public void remove(World world, int i, int j, int k) {
		parent.remove(world, i, j, k);
	}

	@Override
	public int a(Random random) {
		return parent.a(random);
	}

	@Override
	public int getDropCount(int i, Random random) {
		return parent.getDropCount(i, random);
	}

	@Override
	public float getDamage(EntityHuman entityhuman) {
		return parent.getDamage(entityhuman); //could have modified hardness, return super
	}

	@Override
	public void dropNaturally(World world, int i, int j, int k, int l, float f, int i1) {
		parent.dropNaturally(world, i, j, k, l, f, i1);
	}

	@Override
	public MovingObjectPosition a(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1) {
		return parent.a(world, i, j, k, vec3d, vec3d1);
	}

	@Override
	public void wasExploded(World world, int i, int j, int k) {
		parent.wasExploded(world, i, j, k);
	}

	@Override
	public boolean canPlace(World world, int i, int j, int k, int l) {
		return parent.canPlace(world, i, j, k, l);
	}

	@Override
	public boolean canPlace(World world, int i, int j, int k) {
		return parent.canPlace(world, i, j, k);
	}

	@Override
	public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
		return parent.interact(world, i, j, k, entityhuman);
	}

	@Override
	public void b(World world, int i, int j, int k, Entity entity) {
		parent.b(world, i, j, k, entity);
	}

	@Override
	public void postPlace(World world, int i, int j, int k, int l) {
		parent.postPlace(world, i, j, k, l);
	}

	@Override
	public void attack(World world, int i, int j, int k, EntityHuman entityhuman) {
		if (entityhuman instanceof EntityPlayer) {
			SpoutCraftPlayer player = (SpoutCraftPlayer) SpoutManager.getPlayer((Player) ((EntityPlayer) entityhuman).getBukkitEntity());
			player.setLastClickedLocation(new Location(player.getWorld(), i, j, k));
		}
		parent.b(world, i, j, k, entityhuman);
	}

	@Override
	public void a(World world, int i, int j, int k, Entity entity, Vec3D vec3d) {
		parent.a(world, i, j, k, entity, vec3d);
	}

	@Override
	public void updateShape(IBlockAccess iblockaccess, int i, int j, int k) {
		parent.updateShape(iblockaccess, i, j, k);
	}

	@Override
	public boolean a(IBlockAccess iblockaccess, int x, int y, int z, int face) {
		int index = CustomBlock.getIndex(((World) iblockaccess), x, y, z);
		Chunk chunk = ((World) iblockaccess).getChunkAt(x >> 4, z >> 4).bukkitChunk;
		if (chunk.getClass().equals(SpoutCraftChunk.class)) {
			TIntIntHashMap powerOverrides = ((SpoutCraftChunk) chunk).powerOverrides;
			if (powerOverrides.containsKey(index)) {
				int powerbits = powerOverrides.get(index);
				switch (face) {
					case 0:
						return (powerbits & (1 << 0)) != 0;
					case 1:
						return (powerbits & (1 << 1)) != 0;
					case 2:
						return (powerbits & (1 << 2)) != 0;
					case 3:
						return (powerbits & (1 << 3)) != 0;
					case 4:
						return (powerbits & (1 << 4)) != 0;
					case 5:
						return (powerbits & (1 << 5)) != 0;
					default:
						return parent.a(iblockaccess, x, y, z, face);
				}
			}
		}
		return parent.a(iblockaccess, x, y, z, face);
	}

	@Override
	public boolean isPowerSource() {
		return parent.isPowerSource();
	}

	@Override
	public void a(World world, int i, int j, int k, Entity entity) {
		parent.a(world, i, j, k, entity);
	}

	@Override
	public boolean d(World world, int i, int j, int k, int l) {
		return parent.d(world, i, j, k, l);
	}

	@Override
	public void a(World world, EntityHuman entityhuman, int i, int j, int k, int l) {
		parent.a(world, entityhuman, i, j, k, l);
	}

	@Override
	public boolean f(World world, int i, int j, int k) {
		return parent.f(world, i, j, k);
	}

	@Override
	public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
		parent.postPlace(world, i, j, k, entityliving);
	}

	@Override
	public void a(World world, int i, int j, int k, int l, int i1) {
		parent.a(world, i, j, k, l, i1);
	}

	private static void updateField(Block parent, Block child, String fieldName) {
		try {
			Field field = Block.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(child, field.get(parent));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
