package com.example.finans.suite;

import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

import com.example.finans.service.TransacaoServiceTest;
import com.example.finans.service.UsuarioServiceTest;

@Suite
@SuiteDisplayName("Suite de testes")
/*@SelectClasses(value = {
		UsuarioServiceTest.class,
		TransacaoServiceTest.class
})*/
//@SelectPackages("com.example.finans.service")
@SelectPackages(value = {
		"com.example.finans.service",
		"com.example.finans.domain"
})
public class SuiteTest {
	
}
