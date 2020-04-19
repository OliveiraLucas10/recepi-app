package com.oliveiralucaspro.recepi.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oliveiralucaspro.recepi.commands.UnitOfMeasureCommand;
import com.oliveiralucaspro.recepi.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.oliveiralucaspro.recepi.domain.UnitOfMeasure;
import com.oliveiralucaspro.recepi.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
	MockitoAnnotations.initMocks(this);

	service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() throws Exception {
	// given
	Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
	UnitOfMeasure uom1 = new UnitOfMeasure();
	uom1.setId(1L);
	unitOfMeasures.add(uom1);

	UnitOfMeasure uom2 = new UnitOfMeasure();
	uom2.setId(2L);
	unitOfMeasures.add(uom2);

	when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

	// when
	Set<UnitOfMeasureCommand> commands = service.listAllUoms();

	// then
	assertEquals(2, commands.size());
	verify(unitOfMeasureRepository, times(1)).findAll();
    }

}