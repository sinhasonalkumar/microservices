package com.demo.inventory.commandmodule.commands.product;

import com.demo.inventory.commandmodule.commands.BaseCommand;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class DeleteProductCommand extends BaseCommand<String> {

    public DeleteProductCommand(String id) {
        super(id);
    }

}
