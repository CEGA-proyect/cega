package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.NecesidadDAO;

import edu.eci.cvds.samples.entities.Necesidad;
import edu.eci.cvds.samples.services.ServicioNecesidad;
import edu.eci.cvds.samples.services.SolidaridadEscuelaException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ServicioNecesidadImpl implements ServicioNecesidad{

    @Inject 
    private NecesidadDAO necesidadDAO;
    private InputStream inputStream;

    @Override
    public void crearNecesidad(Necesidad n) throws SolidaridadEscuelaException {
        try {
            necesidadDAO.crearNecesidad(n);
        } catch (Exception e) {
            throw new SolidaridadEscuelaException(e.getMessage());
        }
    }



    public List<Necesidad> consultarNombresNecesidad() throws SolidaridadEscuelaException {
        try {
            return necesidadDAO.consultarNombresNecesidad();
        } catch (Exception e) {
            throw new SolidaridadEscuelaException(e.getMessage());

        }
    }

    @Override
    public void actualizarEstadoNecesidad(int id , String estado) throws SolidaridadEscuelaException{
        try{
            necesidadDAO.actualizarEstadoNecesidad(id,estado);
        }catch(Exception e){
            throw new SolidaridadEscuelaException(e.toString());
        }

    }
    @Override
    public int consultarNumeroNecesidadesUsuario(String usuario_id) throws SolidaridadEscuelaException{
        try{
            return necesidadDAO.consultarNumeroNecesidadesUsuario(usuario_id);
        }catch(Exception e){
            throw new SolidaridadEscuelaException(e.toString());
        }
    }

    @Override
    public List<Necesidad> consultarNombresNecesidadGeneral() throws SolidaridadEscuelaException {
        try {
            return necesidadDAO.consultarNombresNecesidadGeneral();
        } catch (Exception e) {
            throw new SolidaridadEscuelaException(e.getMessage());

        }
    }

    @Override
    public String consultarNombreNecesidadPorId(int num) throws SolidaridadEscuelaException{
        try{
            return  necesidadDAO.consultarNombreNecesidadPorId(num);
        }
        catch (Exception e){
            throw new SolidaridadEscuelaException(e.getMessage());
        }
    }

    @Override
    public String consultarMaximoNecesidadesPorUsuario() throws SolidaridadEscuelaException, IOException {
        String result = "";
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream); 
            } else {
                throw new SolidaridadEscuelaException("property file '" + propFileName + "' not found in the classpath");
            }
            result = prop.getProperty("numeromax");
            
        } catch (Exception e) {
            throw new SolidaridadEscuelaException(e.getMessage());
        } finally {   
            inputStream.close();
        }
        return result;
    }

}
