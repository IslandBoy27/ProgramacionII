/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProgramacionII;

/**
 *
 * @author Jayma
 */
public class EmpleadoPorHora extends empleado {
    private int horasT;
    
    public EmpleadoPorHora(int code, String name){
        super(code,name);
    }

    public int getHorasT() {
        return horasT;
    }

    public void setHorasT(int horasT) {
        this.horasT = horasT;
    }
    
    public double pagar(){
        double monto = horasT * 150;
        horasT = 0;
        return monto;
    }
    

    public double bono() {
        if(horasT > 400)
            return 1000;
        return 0;
    }
}