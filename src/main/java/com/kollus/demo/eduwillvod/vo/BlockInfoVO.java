package com.kollus.demo.eduwillvod.vo;

import java.util.HashMap;

public class BlockInfoVO {
    private int block_count;
    private HashMap<String, Long> blocks;
    private HashMap<String, Long> sessions;

    public BlockInfoVO(){}
    public BlockInfoVO(int block_count, HashMap<String, Long> blocks, HashMap<String, Long> sessions) {
        this.block_count = block_count;
        this.blocks = blocks;
        this.sessions = sessions;
    }

    public int getBlock_count() {
        return block_count;
    }

    public void setBlock_count(int block_count) {
        this.block_count = block_count;
    }

    public HashMap<String, Long> getBlocks() {
        return blocks;
    }

    public void setBlocks(HashMap<String, Long> blocks) {
        this.blocks = blocks;
    }

    public HashMap<String, Long> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Long> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return "BlockInfoVO{" +
                "block_count=" + block_count +
                ", blocks=" + blocks +
                ", sessions=" + sessions +
                '}';
    }
}
