import chisel3._
import chisel3.util._
import myparameters_pack._

class multiplier (param:Parameters) extends Module {
  val io = IO(new Bundle {
    val a_in  = Input(UInt((param.PE_DWIDTH).W))
    val b_in  = Input(UInt((param.PE_DWIDTH).W))
    val prod_out = Output(UInt((2*param.PE_DWIDTH).W))
  })


  //stage 1
  //-----------------------------------------------------------------------
  val a_in_reg = Reg(UInt((param.PE_DWIDTH).W))
  val b_in_reg = Reg(UInt((param.PE_DWIDTH).W))

  a_in_reg := io.a_in
  b_in_reg := io.b_in

  //stage 2
  //-----------------------------------------------------------------------

  //---------------------
  //---------------------generic nBits mantissa Partial products splitting
  //number of partial product
  val nPP = ((param.PE_DWIDTH*1.0D) / param.DSPWideInputWidth).ceil.toInt
  //number of DSPs inferred by first mantissa 
  val nAmant = nPP
  //number of DSPs inferred by second mantissa
  val nBmant = ((param.PE_DWIDTH*1.0D) / param.DSPsmallInputWidth).ceil.toInt

  //partial product input operands
  
  //val PPvec = Wire(Vec(nPP, UInt()))
  var seqMulA = Seq.tabulate(1)(i => a_in_reg(param.PE_DWIDTH-1  , 0))
  var seqMulB = Seq.tabulate(1)(i => b_in_reg(param.PE_DWIDTH-1  , 0))

  //assign vector of wires of A which is the first mantissa operand
  if (nAmant > 1) {
    //multiplier large inputs vector
    val mulA = Wire(Vec(nAmant-1, UInt()))
    for (i <- 0 until nAmant-1) {
      mulA(i) := a_in_reg(param.DSPWideInputWidth -1 + (i*param.DSPWideInputWidth), i*param.DSPWideInputWidth)
    }

    val seqA = Seq.tabulate(nAmant-1)(i => mulA(i))
    seqMulA = seqA:+ a_in_reg(param.PE_DWIDTH-1  , ((nAmant-1)*param.DSPWideInputWidth))
  }

  //assign vector of wires of B which is the first mantissa operand
  if(nBmant > 1) {
    //multiplier small inputs vector
    val mulB = Wire(Vec(nBmant-1, UInt()))
    for (i <- 0 until nBmant-1) {
      mulB(i) := b_in_reg(param.DSPsmallInputWidth -1 + (i*param.DSPsmallInputWidth), i*param.DSPsmallInputWidth)
    }
    val seqB = Seq.tabulate(nBmant-1)(i => mulB(i))
    seqMulB = seqB:+ b_in_reg(param.PE_DWIDTH-1  , ((nBmant-1)*param.DSPsmallInputWidth))
  }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//  //val dsp_products = Array.tabulate(nPP, nBmant) ((x, y) =>  (if (param.nMUL_STAGES == 2 || param.nMUL_STAGES == 3) RegNext(seqMulA(x) * seqMulB(y)) else (seqMulA(x) * seqMulB(y)) ))  
//  val dsp_products = Array.tabulate(nPP, nBmant) ((x, y) =>  (if (param.nMUL_STAGES == 2 || param.nMUL_STAGES == 3) ShiftRegister(seqMulA(x) * seqMulB(y),4) else (seqMulA(x) * seqMulB(y)) ))  
//
//
//  //val  partial_products_sum = Array.tabulate(nPP)(x => (if (param.nMUL_STAGES == 3) RegNext(dsp_products(x).tail.zipWithIndex.foldLeft(dsp_products(x)(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPsmallInputWidth)) +& b , a((((j+1)*param.DSPsmallInputWidth)-1),0)),j)}._1 ) else dsp_products(x).tail.zipWithIndex.foldLeft(dsp_products(x)(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPsmallInputWidth)) +& b , a((((j+1)*param.DSPsmallInputWidth)-1),0)),j)}._1 ))
//  val  partial_products_sum = Array.tabulate(nPP)(x => (if (param.nMUL_STAGES == 3) ShiftRegister(dsp_products(x).tail.zipWithIndex.foldLeft(dsp_products(x)(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPsmallInputWidth)) +& b , a((((j+1)*param.DSPsmallInputWidth)-1),0)),j)}._1, 3 ) else dsp_products(x).tail.zipWithIndex.foldLeft(dsp_products(x)(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPsmallInputWidth)) +& b , a((((j+1)*param.DSPsmallInputWidth)-1),0)),j)}._1 ))
//
//  val prod = partial_products_sum.tail.zipWithIndex.foldLeft(partial_products_sum(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPWideInputWidth)) +& b , a((((j+1)*param.DSPWideInputWidth)-1),0)),j)}._1 //sum reduce of all partial products
//
////  //-----------------------------------------------------------------------
//  //val prod_reg = RegNext(prod) 
//  val prod_reg = ShiftRegister(prod,2) 
//
//  io.prod_out := prod_reg


//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

//  //Compute partial products / one PartalProd in each DSP module
//  val dsp_products = Array.tabulate(nPP, nBmant) ((x, y) =>  ShiftRegister(seqMulA(x) * seqMulB(y),param.nMUL_STAGES) )  
//
//  //Compute the partial sums /A better implementation would use foldTree but it seems not supported by chisel in the current / need to check the latest chisel versions 
//  val  partial_products_sum = Array.tabulate(nPP)(x => ShiftRegister(dsp_products(x).tail.zipWithIndex.foldLeft(dsp_products(x)(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPsmallInputWidth)) +& b , a((((j+1)*param.DSPsmallInputWidth)-1),0)),j)}._1, param.nPARTIAL_SUM_STAGES )) 
//
//  //Compute the final sum, that is the sum of all the partial sums / again, using foldTree would probably yield a better implementation however it seems not supported by Chisel for now   
//  val prod = ShiftRegister(partial_products_sum.tail.zipWithIndex.foldLeft(partial_products_sum(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPWideInputWidth)) +& b , a((((j+1)*param.DSPWideInputWidth)-1),0)),j)}._1,param.nTOTAL_SUM_STAGES) //sum reduce of all partial products
//
//  //assign total sum to the product output result
//  io.prod_out := prod
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  //Compute partial products / one PartalProd in each DSP module
  val dsp_products = Array.tabulate(nPP, nBmant) ((x, y) => (if (param.nMUL_STAGES == 0)  (seqMulA(x) * seqMulB(y)) 
                                                                       else  ShiftRegister(seqMulA(x) * seqMulB(y),param.nMUL_STAGES) )  )

  //Compute the partial sums /A better implementation would use foldTree but it seems not supported by chisel in the current / need to check the latest chisel versions 
  val  partial_products_sum = Array.tabulate(nPP)(x => (if (param.nPARTIAL_SUM_STAGES == 0) (dsp_products(x).tail.zipWithIndex.foldLeft(dsp_products(x)(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPsmallInputWidth)) +& b , a((((j+1)*param.DSPsmallInputWidth)-1),0)),j)}._1) 
                                                                          else ShiftRegister(dsp_products(x).tail.zipWithIndex.foldLeft(dsp_products(x)(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPsmallInputWidth)) +& b , a((((j+1)*param.DSPsmallInputWidth)-1),0)),j)}._1, param.nPARTIAL_SUM_STAGES )) )

  //Compute the final sum, that is the sum of all the partial sums / again, using foldTree would probably yield a better implementation however it seems not supported by Chisel for now   
  val prod = if (param.nTOTAL_SUM_STAGES == 0) (partial_products_sum.tail.zipWithIndex.foldLeft(partial_products_sum(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPWideInputWidth)) +& b , a((((j+1)*param.DSPWideInputWidth)-1),0)),j)}._1) 
             else           ShiftRegister(partial_products_sum.tail.zipWithIndex.foldLeft(partial_products_sum(0),0) {case((a,i),(b,j)) => (Cat( (a>>((j+1)*param.DSPWideInputWidth)) +& b , a((((j+1)*param.DSPWideInputWidth)-1),0)),j)}._1,param.nTOTAL_SUM_STAGES) //sum reduce of all partial products

  //assign total sum to the product output result
  io.prod_out := prod

}


