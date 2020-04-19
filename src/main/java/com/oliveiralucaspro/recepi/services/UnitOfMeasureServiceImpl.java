package com.oliveiralucaspro.recepi.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.oliveiralucaspro.recepi.commands.UnitOfMeasureCommand;
import com.oliveiralucaspro.recepi.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.oliveiralucaspro.recepi.repositories.UnitOfMeasureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {

	return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
		.map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
    }
}
