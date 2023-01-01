package com.practica.integracion;

import java.util.ArrayList;

import javax.naming.OperationNotSupportedException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.practica.integracion.DAO.User;
import com.practica.integracion.DAO.AuthDAO;
import com.practica.integracion.DAO.GenericDAO;
import com.practica.integracion.manager.SystemManager;
import com.practica.integracion.manager.SystemManagerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.*;

@ExtendWith(MockitoExtension.class)
//NO SÉ SI LA LÍNEA DE DEBAJO TIENE QUE ESTAR, O ESTOY HACIENDO OTRA COSA MAL
@org.mockito.junit.jupiter.MockitoSettings(strictness = org.mockito.quality.Strictness.LENIENT) 
public class TestValidUser {

	/**
	 * RELLENAR POR EL ALUMNO
	 */

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
			 Mockito.when(genericdao_mock.getSomeData(usuario, "where id=" + "1")).thenReturn(roles);
		 }catch(OperationNotSupportedException e){
			 e.printStackTrace();
		 }
 
		 try{
			 Mockito.when(genericdao_mock.updateSomeData(usuario, "1")).thenReturn(true);
		 }catch(OperationNotSupportedException e){
			 e.printStackTrace();
		 }
 
		 try{
			 Mockito.when(genericdao_mock.deleteSomeData(usuario, "1")).thenReturn(true);
		 }catch(OperationNotSupportedException e){
			 e.printStackTrace();
		 }
 	 
	 }

	 @org.junit.jupiter.api.Test
	 void testStartRemoteSystem(){
		ArrayList<String> esperado= new ArrayList<>();
		esperado.add("empleado");
		esperado.add("jefe");

		try{
			assertEquals(gestor.startRemoteSystem("1", "1"), esperado );
		}catch(SystemManagerException e){
			e.printStackTrace();
		}
		 
	 }
	 @org.junit.jupiter.api.Test
	 void testStopRemoteSystem(){
		ArrayList<String> esperado= new ArrayList<>();
		esperado.add("empleado");
		esperado.add("jefe");

		try{
			assertEquals(gestor.stopRemoteSystem("1", "1"), esperado );
		}catch(SystemManagerException e){
			e.printStackTrace();
		}
		
	 }
	 @org.junit.jupiter.api.Test
	 void testAddRemoteSystem(){
		try{
			gestor.addRemoteSystem("1", "1");
		}catch(SystemManagerException e){
			e.printStackTrace();
			fail();
		} 
	 }
	 @org.junit.jupiter.api.Test
	 void testDeleteRemoteSystem(){

		//CREO QUE FALLA PORQUE EL MÉTODO ESTÁ MAL (CREA UN USUARIO NUEVO RANDOM PARA BORRAR)

		try{
			gestor.deleteRemoteSystem("1", "1");
		}catch(SystemManagerException e){
			e.printStackTrace();
			fail();
		} 
	 }
	
	 
	 
}


