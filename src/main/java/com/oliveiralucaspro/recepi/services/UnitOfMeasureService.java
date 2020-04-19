package com.oliveiralucaspro.recepi.services;

import java.util.Set;

import com.oliveiralucaspro.recepi.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
