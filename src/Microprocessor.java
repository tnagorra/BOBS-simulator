// TODO
// Microprocessor States
// Bus is derivation of Register16
//
// IN OUT
// EI DI
// RIM SIM

import java.io.*;

public class Microprocessor {
    private boolean active;

    private Register16 mar;
    private Register8 mbr;
    private Register8 ir;
    private Register16 pc, sp;

    private Flag flag;
    private Register8[] register;

    private Memory memory;

    // Constructor
    public Microprocessor(Memory mem) {
        // Initialize memory
        memory = mem;
        // Initialize instruction register
        ir = new Register8(0x00);
        // Intialize Program Counter
        pc = new Register16(0x0000);
        // Initialize Stack Pointer
        sp = new Register16(0xFFFF);
        // Initialize flags to zero
        flag = new Flag(0x00);
        // Initialize 8 registers where only 7 will be used
        register = new Register8[8];
        for(int i=0;i<register.length;i++)
            register[i] = new Register8(0);
    }

    // The fetch cycle
    private void fetch(){
        mar = pc.clone();
        Alu.inr(pc);
        mbr = memory.get(mar);
        ir = mbr.clone();
    }

    // The decode and execute cycle
    private void decode(){
        switch( ir.get(7,6) ){
            case 0b01:
                // HLT
                if ( ir.get(5,0) == 0b110110 ) {
                    active = false;
                }
                // MOV
                else {
                    int DDD = ir.get(5,3);
                    int SSS = ir.get(2,0);
                    setR(DDD, getR(SSS));
                }
                break;
            case 0b00:
                // MVI
                if (ir.get(2,0) == 0b110){
                    int DDD= ir.get(5,3);
                    setR(DDD, getData8FromMemory());
                }
                // LXI
                else if ( ir.get(3,0) == 0b0001 ){
                    int DD = ir.get(5,4);
                    if( DD == 0b11){
                        // for SP
                        sp = getData16FromMemory();
                    }
                    else {
                        // for Rp
                        setXL(DD, getData8FromMemory() );
                        setXH(DD, getData8FromMemory() );
                    }
                }
                // NOP
                else if( ir.get(5,0) == 0b00000 ) {
                    // nothing here
                }
                // LDA
                else if( ir.get(5,0) == 0b111010 ) {
                    setA(memory.get( getData16FromMemory() ));
                }
                // STA
                else if( ir.get(5,0) == 0b110010 ) {
                    memory.set(getData16FromMemory(), getA());
                }
                // LHLD
                else if( ir.get(5,0) == 0b101010) {
                    Register16 addr = getData16FromMemory();
                    setL(memory.get(addr));
                    Alu.inr(addr);
                    setH(memory.get(addr));
                }
                // SHLD
                else if( ir.get(5,0) == 0b100010) {
                    Register16 addr = getData16FromMemory();
                    memory.set( addr, getL());
                    Alu.inr(addr);
                    memory.set(addr, getH());
                }
                // LDAX
                else if( ir.get(3,0) == 0b1010 ) {
                    // NOTE
                    // in LDAX and STAX it can't have Rp other than BC and DE
                    // but the previous cases of "if" should be sufficient for validation
                    int DDD = ir.get(5,4);
                    setA( memory.get( getXX(DDD) ));
                }
                // STAX
                else if( ir.get(3,0) == 0b0010 ) {
                    int DDD = ir.get(5,4);
                    memory.set(getXX(DDD) , getA());
                }
                // INR
                else if( ir.get(2,0) == 0b100 ) {
                    int DDD = ir.get(5,3);
                    if(DDD == 0b110)
                        register[DDD] = memory.get(getHL());
                    Flag tflag = Alu.inr(register[DDD]);
                    if(DDD == 0b110)
                        memory.set(getHL(), register[DDD] );
                    // So that carry flag isn't updated
                    tflag.set("C",flag.get("C"));
                    flag = tflag;
                }
                // DCR
                else if( ir.get(2,0) == 0b101 ) {
                    int DDD = ir.get(5,3);
                    if(DDD == 0b110)
                        register[DDD] = memory.get(getHL());
                    Flag tflag = Alu.dcr(register[DDD]);
                    if(DDD == 0b110)
                        memory.set(getHL(), register[DDD] );
                    // So that carry flag isn't updated
                    tflag.set("C",flag.get("C"));
                    flag = tflag;
                }
                // INX
                else if( ir.get(3,0) == 0b0011 ) {
                    int DD = ir.get(5,4);
                    if( DD == 0b11){
                        Alu.inr(sp);
                    } else {
                        Flag tflag = Alu.inr( getXL(DD) );
                        if( tflag.get("C") )
                            Alu.inr( getXH(DD) );
                    }
                }
                // DCX
                else if( ir.get(3,0) == 0b1011 ) {
                    int DD = ir.get(5,4);
                    if( DD == 0b11){
                        Alu.dcr(sp);
                    } else {
                        Flag tflag = Alu.dcr( getXL(DD) );
                        if( tflag.get("C") )
                            Alu.dcr( getXH(DD) );
                    }
                }
                // DAD
                else if( ir.get(3,0) == 0b1001 ) {
                    int DD = ir.get(5,4);
                    if( DD == 0b11){
                        // HEre
                    } else {
                        Flag tflag = Alu.add(getL(), getXL(DD) ,false );
                        tflag = Alu.add(getH(), getXH(DD), tflag.get("C") );
                        flag.set("C",tflag.get("C"));
                    }
                }
                // DAA
                else if( ir.get(5,0) == 0b100111 ) {
                    if( getA().get(3,0) > 0x09 || flag.get("AC") ){
                        Alu.add(getA(), new Register8(0x06),false);
                    }
                    if( getA().get(7,4) > 0x09 || flag.get("C") ){
                        flag = Alu.add(getA(), new Register8(0x60),false);
                    }
                }
                // RLC
                else if( ir.get(5,0) == 0b000111 ) {
                    // get highest bit
                    boolean bit = getA().get(7);
                    Alu.shl(getA());
                    getA().set(0,bit);
                    flag.set("C",bit);
                }
                // RRC
                else if( ir.get(5,0) == 0b001111 ) {
                    // get highest bit
                    boolean bit = getA().get(0);
                    Alu.shr(getA());
                    getA().set(7,bit);
                    flag.set("C",bit);
                }
                // RAL
                else if( ir.get(5,0) == 0b010111 ) {
                    // get highest bit
                    boolean bit = getA().get(7);
                    Alu.shl(getA());
                    getA().set(0,flag.get("C"));
                    flag.set("C",bit);
                }
                // RAR
                else if( ir.get(5,0) == 0b011111 ) {
                    // get highest bit
                    boolean bit = getA().get(0);
                    Alu.shr(getA());
                    getA().set(7,flag.get("C"));
                    flag.set("C",bit);
                }
                // CMA
                else if( ir.get(5,0) == 0b101111) {
                    Alu.cmp(getA());
                }
                // CMC
                else if( ir.get(5,0) == 0b111111) {
                    flag.set("C", ! flag.get("C"));
                }
                // STC
                else if( ir.get(5,0) == 0b110111) {
                    flag.set("C",true);
                }
                // Error
                else {
                    System.out.println("MISSED " +pc.hex()+ " : "+ ir.bin() );
                }
                break;

            case 0b10:
                // ADD
                if ( ir.get(5,3) == 0b000 ){
                    int SSS = ir.get(2,0);
                    flag = Alu.add( getA(), getR(SSS), false );
                }
                // ADC
                else if ( ir.get(5,3) == 0b001 ){
                    int SSS = ir.get(2,0);
                    flag = Alu.add( getA(), getR(SSS), flag.get("C") );
                }
                // SUB
                else if ( ir.get(5,3) == 0b010 ){
                    int SSS = ir.get(2,0);
                    if(SSS == 0b110)
                        flag = Alu.sub( getA(), getR(SSS), false );
                }
                // SBB
                else if ( ir.get(5,3) == 0b011 ){
                    int SSS = ir.get(2,0);
                    flag = Alu.sub( getA(), getR(SSS), flag.get("C") );
                }
                // ANA
                else if ( ir.get(5,3) == 0b100 ){
                    int SSS = ir.get(2,0);
                    flag = Alu.and( getA(), getR(SSS) );
                }
                // XRA
                else if ( ir.get(5,3) == 0b101 ){
                    int SSS = ir.get(2,0);
                    flag = Alu.xor( getA(), getR(SSS));
                }
                // ORA
                else if ( ir.get(5,3) == 0b110 ){
                    int SSS = ir.get(2,0);
                    flag = Alu.or( getA(), getR(SSS) );
                }
                // CMP
                else if ( ir.get(5,3) == 0b111 ){
                    int SSS = ir.get(2,0);
                    flag = Alu.sub( getA().clone() , getR(SSS) , false );
                }
                else {
                    System.out.println("MISSED " +pc.hex()+ " : "+ ir.bin() );
                }

                break;

            case 0b11:
                // ADI
                if ( ir.get(5,0) == 0b000110 ){
                    setT( getData8FromMemory());
                    flag = Alu.add( getA(), getT() , false );
                }
                // ACI
                else if ( ir.get(5,0) == 0b001110 ){
                    setT( getData8FromMemory());
                    flag = Alu.add( getA(), getT(), flag.get("C") );
                }
                // SUI
                else if ( ir.get(5,0) == 0b010110 ){
                    setT( getData8FromMemory());
                    flag = Alu.sub( getA(), getT(), false );
                }
                // SBI
                else if ( ir.get(5,0) == 0b011110 ){
                    setT( getData8FromMemory());
                    flag = Alu.sub( getA(), getT(), flag.get("C") );
                }
                // ANI
                else if ( ir.get(5,0) == 0b100110){
                    setT( getData8FromMemory());
                    flag = Alu.and( getA(), getT() );
                }
                // XRI
                else if ( ir.get(5,0) == 0b101110){
                    setT( getData8FromMemory());
                    flag = Alu.xor( getA(), getT() );
                }
                // ORI
                else if ( ir.get(5,0) == 0b110110){
                    setT( getData8FromMemory());
                    flag = Alu.or( getA(), getT() );
                }
                // CPI
                else if ( ir.get(5,0) == 0b111110 ){
                    setT( getData8FromMemory());
                    // Register mustn't be changed
                    flag = Alu.sub( getA().clone() , getT(), false );
                }
                // XCHG
                else if( ir.get(5,0) == 0b101011 ) {
                    Register8 temp = register[2];
                    register[2] = register[4];
                    register[4] = temp;
                    temp = register[3];
                    register[3] = register[5];
                    register[5] = temp;
                }
                // PUSH
                else if( ir.get(3,0) == 0b0101) {
                    int SS = ir.get(5,4);
                    if( SS == 0b11){
                        // for M
                        Alu.dcr(sp);
                        memory.set(sp, getA() );
                        Alu.dcr(sp);
                        memory.set(sp, flag );
                    } else {
                        // for Rp
                        Alu.dcr(sp);
                        memory.set(sp, getXH(SS) );
                        Alu.dcr(sp);
                        memory.set(sp, getXL(SS));
                    }
                }
                // POP
                else if( ir.get(3,0) == 0b0001) {
                    int DD = ir.get(5,4);
                    if( DD == 0b11){
                        // for M
                        flag.set(memory.get(sp));
                        Alu.inr(sp);
                        setA(memory.get(sp));
                        Alu.inr(sp);
                    } else {
                        // for Rp
                        setXL( DD, memory.get(sp) );
                        Alu.inr(sp);
                        setXH( DD, memory.get(sp) );
                        Alu.inr(sp);
                    }
                }
                // JMP
                else if( ir.get(5,0) == 0b000011){
                    pc = getData16FromMemory();
                }
                // CALL
                else if( ir.get(5,0) == 0b001101){
                    pushPC();
                    pc = getData16FromMemory();
                }
                // RST
                else if( ir.get(2,0) == 0b111){
                    pushPC();
                    pc = new Register16( ir.get(5,3)*8 );
                }
                // RET
                else if( ir.get(5,0) == 0b001001){
                    popPC();
                }
                // PCHL
                else if( ir.get(5,0) == 0b101001){
                    pc = getHL();
                }
                // SPHL
                else if( ir.get(5,0) == 0b111001){
                    sp = getHL();
                }
                // XTHL
                else if( ir.get(5,0) == 0b100011){
                    Register16 tadr = sp.clone();

                    Register8 treg = memory.get(tadr);
                    memory.set( tadr , register[5] );
                    register[5] = treg.clone();

                    Alu.inr(tadr);

                    treg = memory.get(tadr);
                    memory.set( tadr , register[4] );
                    register[4] = treg.clone();
                }
                // JX
                else if( ir.get(2,0) == 0b010) {
                    if( getCondition( ir.get(5,3) ) ) {
                        pc = getData16FromMemory();
                    } else {
                        Alu.inr(pc);
                        Alu.inr(pc);
                    }
                }
                // CX
                else if( ir.get(2,0) == 0b100) {
                    if(getCondition(ir.get(5,3))) {
                        pushPC();
                        pc = getData16FromMemory();
                    } else {
                        Alu.inr(pc);
                        Alu.inr(pc);
                    }
                }
                // RX
                else if( ir.get(2,0) == 0b000) {
                    if(getCondition(ir.get(5,3))) {
                        popPC();
                    }
                }
                else {
                    System.out.println("MISSED " +pc.hex()+ " : "+ ir.bin() );
                }
                break;

            default:
                System.out.println("MISSED " +pc.hex()+ " : "+ ir.bin() );
                break;
        }
    }

    // Start the microprocessor operation
    public void start(Register16 mem, boolean verbose,boolean singlestep) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        active = true;
        pc = mem.clone();
        while(active){
            fetch();
            decode();

            if(singlestep){
                print(verbose);

                String y = br.readLine();
                if( y.equals("Q") || y.equals("q") )
                    active = false;
                if( y.equals("B") || y.equals("b") )
                    singlestep = false;
                if( y.equals("V") || y.equals("v") )
                    verbose = true;
                if( y.equals("NV") || y.equals("nv") )
                    verbose = false;
            }
        }
    }

    // Print the status of the program
    public void print(boolean verbose){
        if(verbose){
            flag.print();
            System.out.println("PSW\t: "+register[7].hex()+" "+flag.hex());
            System.out.println("BC\t: "+register[0].hex()+" "+register[1].hex());
            System.out.println("DE\t: "+register[2].hex()+" "+register[3].hex());
            System.out.println("HL\t: "+register[4].hex()+" "+register[5].hex());
        }
        System.out.println("IR\t: "+ir.hex());
        System.out.println("PC\t: "+pc.hex());
        if(verbose){
            System.out.println("SP\t: "+sp.hex());
        }
    }

    // Memory to Register Transfer

    private Register8 getData8FromMemory(){
        Register8 D = memory.get(pc);
        Alu.inr(pc);
        return D;
    }

    private Register16 getData16FromMemory(){
        return new Register16(getData8FromMemory(),getData8FromMemory());
    }

    // Push and Pop operations on PC

    private void pushPC(){
        Alu.dcr(sp);
        memory.set(sp, new Register8(pc.upper()));
        Alu.dcr(sp);
        memory.set(sp, new Register8(pc.lower()));
    }
    private void popPC(){
        Register8 D2 = memory.get(sp);
        Alu.inr(sp);
        Register8 D1 = memory.get(sp);
        Alu.inr(sp);
        pc = new Register16(D2,D1);
    }


    // Condition after decoding
    private boolean getCondition(int CCC){
        if( (CCC==0b000 && !flag.get("Z")) ||
                (CCC==0b001 && flag.get("Z")) ||
                (CCC==0b010 && !flag.get("C")) ||
                (CCC==0b011 && flag.get("C")) ||
                (CCC==0b100 && !flag.get("P")) ||
                (CCC==0b101 && flag.get("P")) ||
                (CCC==0b110 && !flag.get("S")) ||
                (CCC==0b111 && flag.get("S")) )
            return true;
        return false;
    }

    // Register Pair access after decoding

    private void setXH(int DD, Register8 val){
        // CHECK for SP
        register[DD*2] = val.clone();
    }
    private void setXL(int DD, Register8 val){
        // CHECK for SP
        register[DD*2+1] = val.clone();
    }
    private Register8 getXH(int DD){
        // CHECK for SP
        return register[DD*2];
    }
    private Register8 getXL(int DD){
        // CHECK for SP
        return register[DD*2+1];
    }

    private Register16 getXX(int DD){
        // CHECK for SP
        if(DD==0b11)
            return sp.clone();
        return new Register16(getXL(DD), getXH(DD));
    }

    // Register access after decoding

    private void setR(int DDD, Register8 value){
        register[DDD] = value.clone();
        if(DDD == 0b110)
            memory.set( getHL() , register[DDD] );
    }
    private Register8 getR(int DDD){
        if(DDD == 0b110)
            register[DDD] = memory.get(getHL());
        return register[DDD];
    }

    // Some generic register access functions

    private Register16 getHL(){
        return getXX(0x02);
    }

    private void setA(Register8 val){
        register[7] = val;
    }
    private void setH(Register8 val){
        register[4] = val;
    }
    private void setL(Register8 val){
        register[5] = val;
    }
    private void setT(Register8 value){
        register[6] = value;
    }

    private Register8 getH(){
        return register[4];
    }
    private Register8 getL(){
        return register[5];
    }
    private Register8 getA(){
        return register[7];
    }
    private Register8 getT(){
        return register[6];
    }

}