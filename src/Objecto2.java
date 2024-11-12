import java.awt.*;
import java.util.ArrayList;

public class Objecto2 {
    public static final String SQUARE = "square";
    public static final String OVAL = "oval";
    public static final String LINE = "line";
    public static final String IMAGE = "image";

    public boolean static_ = true;
    public boolean debug = false;
    public float[] position;
    public float[] momentum;
    public int[] size;
    public String type;
    public Image image;
    public float drag;
    public float gravity;
    public float bounce;
    public Color color;
    public boolean solid;
    private ArrayList<Objecto2> objs;

    //questa classe permetterà una gestione facilitata degli oggetti con attributi tipo posizione e grandezza
    public Objecto2(int posX, int posY, int width, int height, String type) {
        init(posX,posY,width,height,type,new Color(0x000000),true,true);
    }
    public Objecto2(int posX, int posY, int width, int height, String type, Color color){
        init(posX,posY,width,height,type,color,true, true);
    }
    public Objecto2(int posX, int posY, int width, int height, String type, Color color, boolean solid){
        init(posX,posY,width,height,type,color,solid,true);
    }
    public Objecto2(int posX, int posY, int width, int height, String type, Color color, boolean solid, boolean static_){
        init(posX,posY,width,height,type,color,solid,static_);
    }
    private void init(int posX, int posY, int width, int height, String type,Color color,boolean solid, boolean static_){
        this.type = type;
        position = new float[]{posX, posY};
        size = new int[]{width,height};
        momentum = new float[]{0,0};
        drag = 1;
        gravity = 0f;
        objs = new StupidBounces().getObjs2();
        bounce = 0f;
        this.static_ = static_;
        this.color = color;
        this.solid = solid;
    }
    public Objecto2(int posX, int posY, int width, int height, Image image){
        this.type = IMAGE;
        this.image = image;
        position = new float[]{posX, posY};
        size = new int[]{width,height};
    }

    public void addForce(int xForce, int yForce){
        this.position[0] += xForce;
        this.position[1] += yForce;
        momentum[0] += xForce;
        momentum[1] += yForce;
    }

    public void update(){
        if (static_) return;

        momentum[0] -= momentum[0] * (drag);
        momentum[1] -= momentum[1] * (drag) - gravity;

        this.position[0] += (float) (momentum[0]*Clock.deltaTime);
        this.position[1] += (float) (momentum[1]*Clock.deltaTime);

        ArrayList<Collision> cols = new ArrayList<>();
        if(solid){
            cols = checkCollision();
        }
        for(Collision c:cols){
        }
    }
    public ArrayList<Collision> checkCollision(){
        ArrayList<Collision> collisions = new ArrayList<>();
        for (Objecto2 obj:objs){

        }
        return collisions;
    }
    public void setDrag(float drag){
        this.drag = drag;
    }
    public void move(float x, float y){
        this.position[0] += x;
        this.position[1]+=y;
    }
    public int collideWith(Objecto2 o){
        switch (o.type){
            case Objecto2.LINE:
                return collideWithLine(o);
            default:
                return collideWithSquare(o);
        }
    }
    private int collideWithSquare(Objecto2 o){
        //b  = se due oggetti si toccano
        if(!o.solid){
            return 0;
        }
        boolean b=false;
        double cambio;

        double altezza2=size[1];
        double base2=size[0];
        double altezza1=o.size[1];
        double base1=o.size[0];
        double x2=getCenter()[0];
        double y2=getCenter()[1];
        double x1=o.getCenter()[0];
        double y1=o.getCenter()[1];
        if(x2>x1){
            cambio=x1;
            x1=x2;
            x2=cambio;
        }
        if(y2>y1){
            cambio=y1;
            y1=y2;
            y2=cambio;
        }
        if(altezza1/2+altezza2/2>y1-y2&&base1/2+base2/2>x1-x2){
            b = true;
        }

        double posX1 = getCenter()[0];
        double posY1 = getCenter()[1];
        double posX2 = o.getCenter()[0];
        double posY2 = o.getCenter()[1];

        if(b){
            float[] f1;
            float[] f2;
            if(posX1>=posX2 && posY1 >= posY2){       //basso destra
                f1 = new float[]{position[0], position[1]};
                f2 = new float[]{o.position[0]+o.size[0],o.position[1]+o.size[1]};


                if(Math.abs(f1[0]-f2[0]) > Math.abs(f1[1]-f2[1])){
                    adjustPosition(o,3,f1,f2);
                    return 3;
                }else{
                    adjustPosition(o,2,f1,f2);
                    return 2;
                }
            }else if(posX1<posX2 && posY1 >= posY2){  //basso sinistra
                f1 = new float[]{position[0]+size[0], position[1]};
                f2 = new float[]{o.position[0],o.position[1]+o.size[1]};

                if(Math.abs(f1[0]-f2[0]) > Math.abs(f1[1]-f2[1])){
                    adjustPosition(o,3,f1,f2);
                    return 3;
                }else{
                    adjustPosition(o,4,f1,f2);
                    return 4;
                }
            }else if(posX1<posX2){                    //alto sinistra
                f1 = new float[]{position[0]+size[0], position[1]+size[1]};
                f2 = new float[]{o.position[0],o.position[1]};

                if(Math.abs(f1[0]-f2[0]) > Math.abs(f1[1]-f2[1])){
                    adjustPosition(o,1,f1,f2);
                    return 1;
                }else{
                    adjustPosition(o,4,f1,f2);
                    return 4;
                }
            }else {                                   //alto destra
                f1 = new float[]{position[0], position[1]+size[1]};
                f2 = new float[]{o.position[0]+o.size[0],o.position[1]};

                if(Math.abs(f1[0]-f2[0]) > Math.abs(f1[1]-f2[1])){
                    adjustPosition(o,1,f1,f2);
                    return 1;
                }else{
                    adjustPosition(o,2,f1,f2);
                    return 2;
                }
            }

        }
        return 0;
    }
    private int collideWithLine(Objecto2 o){
        float[] center = getCenter();

        float lineM = (o.position[0]-o.size[0])/(o.position[1]-o.size[1]);//coefficiente angolare retta
        float lineQ = o.position[1]-lineM*o.position[0];//termine noto retta
        float[] angPos;
        if(center[1]>=lineM*center[0]+lineQ){ //vuol dire che sta sotto alla linea
            if(lineM>=0){ //angolo alto destra
                angPos = getAnglePos(1);
            }else{ //angolo alto sinistra
                 angPos = getAnglePos(4);
            }
        }else{ //vuol dire che sta sopra alla linea
            if(lineM>=0){ //angolo basso sinistra
                 angPos = getAnglePos(2);
            }else{ // angolo basso destra
                 angPos = getAnglePos(3);
            }
        }
        float incidentX = ((angPos[1]-momentum[0]/momentum[1]*angPos[0])-lineM)/(lineQ-momentum[0]/momentum[1]);
        float incidentY = lineM*incidentX + lineQ;

        move(incidentX-angPos[0],incidentY-angPos[1]);
        return 0;
    }

    public float[] getCenter(){
        return new float[]{position[0]+(float)size[0]/2,position[1]+(float)size[1]/2};
    }

    /*
        4-------1
        |       |
        |       |
        3-------2
    */
    public float[] getAnglePos(int anglenum){
        switch (anglenum){
            case 1: //alto destra
                return new float[]{position[0]+size[0],position[1]};
            case 2: //basso destra
                return new float[]{position[0]+size[0],position[1]+size[1]};
            case 3: //basso sinistra
                return new float[]{position[0],position[1]+size[1]};
            default: //:4 alto sinistra
                return new float[]{position[0],position[1]};
        }
    }

    private void adjustPosition(Objecto2 o, int colDirection, float[] f1, float[] f2 ){
        switch (colDirection) {
            case 1: // io arrivo da sopra
                if(o.static_){

                    float f = Math.abs(f1[1]-f2[1]);
                    if(debug) System.out.println("sistemato di "+f+"?");
                    position[1] -= f;
                }
                break;
            case 2: // io arrivo da destra
                if(o.static_){

                    float f = Math.abs(f1[0]-f2[0]);
                    if(debug){
                        System.out.println("sistemato di "+f+"?");
                    }
                    position[0] += f;
                }
                break;
            case 3: // io arrivo da giu
                if(o.static_){

                    float f = Math.abs(f1[1]-f2[1]);
                    if(debug){
                        System.out.println("sistemato di "+f+"?");

                    }
                    position[1] += f;
                }
                break;
            case 4: // io arrivo da sinistra
                if(o.static_){

                    float f = Math.abs(f1[0]-f2[0]);
                    if (debug) System.out.println("sistemato di "+f+"?");
                    position[0] -= f;
                }
        }
    }
}
