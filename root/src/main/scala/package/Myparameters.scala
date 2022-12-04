package myparameters_pack

abstract class Parameters {
   def PE_DWIDTH           : Int  // The operands input width of the multiplier 
   def DSPWideInputWidth   : Int  // The input width of the wide input operand on the DSP   
   def DSPsmallInputWidth  : Int  // The input width of the small input operand on the DSP   
   //pipeline stages// Here the generator is expected to insert multi-pipeline regs only after each completion of the three computation steps (multiplication, partial sums, total sum)
   // Retiming should be selected in the FPGA compilation flow to balance critical paths and absorb the multiplication pipeline registers inside DSP48 modules 
   def nMUL_STAGES         : Int  // The number of pipeline stages for the partial products multiplication
   def nPARTIAL_SUM_STAGES : Int  // The number of pipeline stages for the partial summation of partial products 
   def nTOTAL_SUM_STAGES   : Int  // The number of pipeline stages for the total summation of partial sums
}

object myparams extends Parameters{
   val PE_DWIDTH          = 53
   val DSPWideInputWidth  = 26
   val DSPsmallInputWidth = 17
   val nMUL_STAGES        = 0
   val nPARTIAL_SUM_STAGES= 0
   val nTOTAL_SUM_STAGES  = 1
}
