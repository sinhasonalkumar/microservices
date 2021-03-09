package com.demo.inventory.command.commands.product;


import com.demo.inventory.command.commands.BaseCommand;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class DeleteProductCommand extends BaseCommand<String> {

    public DeleteProductCommand(String id) {
        super(id);
    }

}
