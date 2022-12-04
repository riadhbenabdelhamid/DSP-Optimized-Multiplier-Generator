import chisel3._ 
import chisel3.iotesters._
import myparameters_pack._
import scala.math._
import BigInt.probablePrime
import scala.util.Random

class multiplier_tester (dut: multiplier) extends PeekPokeTester (dut) {

  //check maximum value inputs
  var a:BigInt =scala.math.pow(2,myparams.PE_DWIDTH).longValue-1
  var b:BigInt =scala.math.pow(2,myparams.PE_DWIDTH).longValue-1
  var c:BigInt =a*b 


  println("Binary values")
  println("a is: " + a.toString(2))
  println("b is: " + b.toString(2))
  println("c is: " + c.toString(2))
  println("Decimal values")
  println("a is: " + a.toString(10))
  println("b is: " + b.toString(10))
  println("c is: " + c.toString(10))

  poke(dut.io.a_in,a)
  poke(dut.io.b_in,b)

  step (myparams.nMUL_STAGES+myparams.nPARTIAL_SUM_STAGES+myparams.nTOTAL_SUM_STAGES+1)
  expect(dut.io.prod_out,c)
  println("Hardware Computed Result is: " +peek(dut.io.prod_out))

  //check 0 value input a
  a =0
  b =scala.math.pow(2,myparams.PE_DWIDTH).longValue-1
  c =a*b 


  println("Binary values")
  println("a is: " + a.toString(2))
  println("b is: " + b.toString(2))
  println("c is: " + c.toString(2))
  println("Decimal values")
  println("a is: " + a.toString(10))
  println("b is: " + b.toString(10))
  println("c is: " + c.toString(10))

  poke(dut.io.a_in,a)
  poke(dut.io.b_in,b)

  step (myparams.nMUL_STAGES+myparams.nPARTIAL_SUM_STAGES+myparams.nTOTAL_SUM_STAGES+1)
  expect(dut.io.prod_out,c)
  println("Hardware Computed Result is: " +peek(dut.io.prod_out))

  //check 0 value input b
  a =scala.math.pow(2,myparams.PE_DWIDTH).longValue-1
  b =0
  c =a*b 


  println("Binary values")
  println("a is: " + a.toString(2))
  println("b is: " + b.toString(2))
  println("c is: " + c.toString(2))
  println("Decimal values")
  println("a is: " + a.toString(10))
  println("b is: " + b.toString(10))
  println("c is: " + c.toString(10))

  poke(dut.io.a_in,a)
  poke(dut.io.b_in,b)

  step (myparams.nMUL_STAGES+myparams.nPARTIAL_SUM_STAGES+myparams.nTOTAL_SUM_STAGES+1)
  expect(dut.io.prod_out,c)
  println("Hardware Computed Result is: " +peek(dut.io.prod_out))

  //check neutral value 1 input a
  a =1
  b =scala.math.pow(2,myparams.PE_DWIDTH).longValue-1
  c =a*b 


  println("Binary values")
  println("a is: " + a.toString(2))
  println("b is: " + b.toString(2))
  println("c is: " + c.toString(2))
  println("Decimal values")
  println("a is: " + a.toString(10))
  println("b is: " + b.toString(10))
  println("c is: " + c.toString(10))

  poke(dut.io.a_in,a)
  poke(dut.io.b_in,b)

  step (myparams.nMUL_STAGES+myparams.nPARTIAL_SUM_STAGES+myparams.nTOTAL_SUM_STAGES+1)
  expect(dut.io.prod_out,c)
  println("Hardware Computed Result is: " +peek(dut.io.prod_out))

  //check neutral value 1 input b
  a =scala.math.pow(2,myparams.PE_DWIDTH).longValue-1
  b =1
  c =a*b 


  println("Binary values")
  println("a is: " + a.toString(2))
  println("b is: " + b.toString(2))
  println("c is: " + c.toString(2))
  println("Decimal values")
  println("a is: " + a.toString(10))
  println("b is: " + b.toString(10))
  println("c is: " + c.toString(10))

  poke(dut.io.a_in,a)
  poke(dut.io.b_in,b)

  step (myparams.nMUL_STAGES+myparams.nPARTIAL_SUM_STAGES+myparams.nTOTAL_SUM_STAGES+1)
  expect(dut.io.prod_out,c)
  println("Hardware Computed Result is: " +peek(dut.io.prod_out))


  //check product of random value inputs
  for (i <- 0 until 100) {
    a = probablePrime(myparams.PE_DWIDTH, Random)
    b = probablePrime(myparams.PE_DWIDTH, Random)
    c =a*b 


    println("Binary values")
    println("a is: " + a.toString(2))
    println("b is: " + b.toString(2))
    println("c is: " + c.toString(2))
    println("Decimal values")
    println("a is: " + a.toString(10))
    println("b is: " + b.toString(10))
    println("c is: " + c.toString(10))

    poke(dut.io.a_in,a)
    poke(dut.io.b_in,b)

    step (myparams.nMUL_STAGES+myparams.nPARTIAL_SUM_STAGES+myparams.nTOTAL_SUM_STAGES+1)
    expect(dut.io.prod_out,c)
    println("Hardware Computed Result is: " +peek(dut.io.prod_out))
  }

}

object multiplier_tester extends App {
  chisel3.iotesters.Driver.execute(Array[String]("--generate-vcd-output", "on"), () => new multiplier(myparams)) {
    c => new multiplier_tester(c)
  }
}

