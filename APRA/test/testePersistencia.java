/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.VoluntarioDAO;
import dominio.Voluntario;
import org.junit.Test;

/**
 *
 * @author lucas
 */
public class testePersistencia {
    
    public testePersistencia() {
    
    }
    
    @Test
    public void TesteSalvar(){
            Voluntario voluntario = new Voluntario(null,"Lucas Nogueira","lucas@gmail.com","4712383923","06/02/1998","Socorrista");
            new VoluntarioDAO().salvar(voluntario);
    }
}
