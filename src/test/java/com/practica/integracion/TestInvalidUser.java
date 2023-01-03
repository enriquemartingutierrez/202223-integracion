package com.practica.integracion;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.practica.integracion.DAO.User;
import com.practica.integracion.DAO.AuthDAO;
import com.practica.integracion.DAO.GenericDAO;
import com.practica.integracion.manager.SystemManager;
import com.practica.integracion.manager.SystemManagerException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;



@ExtendWith(MockitoExtension.class)
@org.mockito.junit.jupiter.MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT) 

public class TestInvalidUser {

	@org.mockito.Mock 
	AuthDAO authdao_mock;
	@org.mockito.Mock 
	GenericDAO genericdao_mock;

	SystemManager gestor;
	User usuario;

	@org.junit.jupiter.api.BeforeEach
	void init(){
		gestor= new SystemManager(authdao_mock,genericdao_mock);
		List<Object> roles= new ArrayList<Object>();
		roles.add("empleado");
		roles.add("jefe");

		usuario= new User("1", "Luisito", "Comunica","luisitocomunica@correo.com",roles );

		Mockito.when(authdao_mock.getAuthData("1")).thenReturn(usuario);

		try{
			Mockito.when(genericdao_mock.getSomeData(usuario, "where id=" + "1")).thenThrow(new OperationNotSupportedException());
		}catch(OperationNotSupportedException e){
			e.printStackTrace();
		}

		try{
			Mockito.when(genericdao_mock.updateSomeData(usuario, "1")).thenThrow(new OperationNotSupportedException());
		}catch(OperationNotSupportedException e){
			e.printStackTrace();
		}

		try{
			Mockito.when(genericdao_mock.deleteSomeData(usuario, "1")).thenThrow(new OperationNotSupportedException());
		}catch(OperationNotSupportedException e){
			e.printStackTrace();
		}
	 
	}

	@org.junit.jupiter.api.Test
	void testStartRemoteSystem(){

	   assertThrows(SystemManagerException.class, ()->gestor.startRemoteSystem("1", "1") );
	
		
	}
	@org.junit.jupiter.api.Test
	void testStopRemoteSystem(){

	   assertThrows(SystemManagerException.class, ()->gestor.stopRemoteSystem("1", "1") );
	   
	}
	@org.junit.jupiter.api.Test
	void testAddRemoteSystem(){
		assertThrows(SystemManagerException.class, ()->gestor.addRemoteSystem("1", "1") );
	}
	@org.junit.jupiter.api.Test
	void testDeleteRemoteSystem(){

		assertThrows(SystemManagerException.class, ()->gestor.deleteRemoteSystem("1", "1") );
	}


}
