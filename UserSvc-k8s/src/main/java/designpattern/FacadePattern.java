/*
 * $Id$
 */
package designpattern;

public class FacadePattern {
  public static void main(String[] args) {
    Computer computer = new Computer();
    computer.run();
  }
  
  static class CPU {
    public void processData() { }
  }

  static class Memory {
    public void load() { }
  }

  static class HardDrive {
    public void readdata() { }
  }

  /* Facade */
  static class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void run() {
        cpu.processData();
        memory.load();
        hardDrive.readdata();
    }
  }
}
