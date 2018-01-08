package org.bukkit.craftbukkit;

import org.bukkit.Chunk;
import org.bukkit.PlayerChunk;

import net.minecraft.server.PlayerChunkMap;
import net.minecraft.server.WorldServer;

public class CraftPlayerChunk extends AbstractWrapper<net.minecraft.server.PlayerChunk> implements PlayerChunk {
    private final WorldServer world;
    private final int x;
    private final int z;

    public CraftPlayerChunk(net.minecraft.server.PlayerChunk playerChunk) {
        super(playerChunk);
        world = playerChunk.chunk.world.worldData.world;
        this.x = playerChunk.chunk.locX;
        this.z = playerChunk.chunk.locZ;
    }

    @Override
    protected net.minecraft.server.PlayerChunk retrieve() {
        return world.getPlayerChunkMap().getChunk(x, z);
    }

    @Override
    public Chunk getChunk() {
        return new CraftChunk(getHandle().chunk);
    }

    /**
     * @return {@code true} whether this player chunk will remain loaded even if it has no player associated anymore
     */
    @Override
    public boolean isSticky() {
        return getHandle().isSticky();
    }

    /**
     * Forces this player chunk to remain in its {@link #playerChunkMap} even if it contains no player entities anymore,
     * or releases the chunk if {@code sticky} is {@code false}.
     */
    @Override
    public void setSticky(boolean sticky) {
        getHandle().setSticky(sticky);
    }
}
