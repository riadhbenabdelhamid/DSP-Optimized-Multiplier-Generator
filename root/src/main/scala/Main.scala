import chisel3._
import chisel3.util._
import myparameters_pack._
import chisel3.stage.ChiselStage
import chisel3.stage.ChiselGeneratorAnnotation

object Main extends App {

  (new ChiselStage).execute(
  Array(
    "-X", "verilog",
    "-e", "verilog",
    "--target-dir", "genrtl"),
  Seq(ChiselGeneratorAnnotation(() => new multiplier(myparams))))

}

