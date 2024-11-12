public class Collision {
    public float[] normal;
    public float[] pos;
    public Objecto obj2move;
    public Objecto obj;

    public Collision(Objecto obj2move, Objecto obj) {
        this.obj2move = obj2move;
        this.obj =obj;
    }

    public double getAngleBetween(){
        float lineM = (obj.position[1]-obj.size[1])/(obj.position[0]-obj.size[0]);//coefficiente angolare retta
        float momentumM = obj2move.momentum[1]/obj2move.momentum[0];
        return Math.atan(Math.abs((lineM-momentumM)/(lineM*momentumM+1))); //in radiant
    }


    public float[] getPosAdjust(){
        float[] center = obj2move.getCenter();

        float lineM = (obj.position[1]-obj.size[1])/(obj.position[0]-obj.size[0]);//coefficiente angolare retta
        float lineQ = obj.position[1]-lineM*obj.position[0];//termine noto retta
        //System.out.println(getVelocity() + " [ENTER] --> "+getMomentum()+"; "+lineM+"x+"+lineQ);

        float[] angPos;
        if(center[1]>=lineM*center[0]+lineQ){ //vuol dire che sta sotto alla linea
            if(lineM>=0){ //angolo alto destra
                angPos = obj2move.getAnglePos(1);

            }else{ //angolo alto sinistra
                angPos = obj2move.getAnglePos(4);
            }
        }else{ //vuol dire che sta sopra alla linea
            if(lineM>=0){ //angolo basso sinistra
                angPos = obj2move.getAnglePos(3);
            }else{ // angolo basso destra
                angPos = obj2move.getAnglePos(2);
            }
        }
        float momentumM = obj2move.momentum[1]/obj2move.momentum[0];
        float momentumQ = angPos[1]-momentumM*angPos[0];
        if(lineM-momentumM == 0){
            return null;
        }
        float incidentX = (momentumQ-lineQ)/(lineM-momentumM);

        float incidentY = lineM*incidentX + lineQ;
        return new float[]{angPos[0]-incidentX,angPos[1]-incidentY};
    }

    public float[] getVelocityResult(){
        return null;
    }

}
