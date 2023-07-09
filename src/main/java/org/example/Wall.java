package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Wall implements Structure {
    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (findBlockByColorRecursive(block, color))
                return Optional.of(block);
        }
        return Optional.empty();
    }

    private boolean findBlockByColorRecursive(Block block, String color) {
        if (block.getColor().equals(color)) {
            return true;
        }

        if (block instanceof CompositeBlock compositeBlock) {
            for (Block subBlock : compositeBlock.getBlocks()) {
                if (findBlockByColorRecursive(subBlock, color)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> resultBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (findBlockByMaterialRecursive(block, material)) {
                resultBlocks.add(block);
            }
        }
        return resultBlocks;
    }

    private boolean findBlockByMaterialRecursive(Block block, String material) {
        if (block.getMaterial().equals(material)) {
            return true;
        }

        if (block instanceof CompositeBlock compositeBlock) {
            for (Block subBlock : compositeBlock.getBlocks()) {
                if (findBlockByMaterialRecursive(subBlock, material))
                    return true;
            }
        }

        return false;
    }

    @Override
    public int count() {
        int totalCount = 0;
        for (Block block : blocks) {
            if (block instanceof CompositeBlock compositeBlock) {
                totalCount += compositeBlock.getBlocks().size();
            } else {
                totalCount++;
            }
        }
        return totalCount;
    }
}

