module multiplier(
  input          clock,
  input          reset,
  input  [52:0]  io_a_in,
  input  [52:0]  io_b_in,
  output [105:0] io_prod_out
);
`ifdef RANDOMIZE_REG_INIT
  reg [63:0] _RAND_0;
  reg [63:0] _RAND_1;
  reg [127:0] _RAND_2;
`endif // RANDOMIZE_REG_INIT
  reg [52:0] a_in_reg; // @[multiplier.scala 15:21]
  reg [52:0] b_in_reg; // @[multiplier.scala 16:21]
  wire  seqMulA_2 = a_in_reg[52]; // @[multiplier.scala 48:30]
  wire [1:0] seqMulB_3 = b_in_reg[52:51]; // @[multiplier.scala 59:30]
  wire [42:0] _T_7 = a_in_reg[25:0] * b_in_reg[16:0]; // @[multiplier.scala 94:103]
  wire [42:0] _T_8 = a_in_reg[25:0] * b_in_reg[33:17]; // @[multiplier.scala 94:103]
  wire [42:0] _T_9 = a_in_reg[25:0] * b_in_reg[50:34]; // @[multiplier.scala 94:103]
  wire [27:0] _T_10 = a_in_reg[25:0] * seqMulB_3; // @[multiplier.scala 94:103]
  wire [42:0] _T_11 = a_in_reg[51:26] * b_in_reg[16:0]; // @[multiplier.scala 94:103]
  wire [42:0] _T_12 = a_in_reg[51:26] * b_in_reg[33:17]; // @[multiplier.scala 94:103]
  wire [42:0] _T_13 = a_in_reg[51:26] * b_in_reg[50:34]; // @[multiplier.scala 94:103]
  wire [27:0] _T_14 = a_in_reg[51:26] * seqMulB_3; // @[multiplier.scala 94:103]
  wire [17:0] _T_15 = seqMulA_2 * b_in_reg[16:0]; // @[multiplier.scala 94:103]
  wire [17:0] _T_16 = seqMulA_2 * b_in_reg[33:17]; // @[multiplier.scala 94:103]
  wire [17:0] _T_17 = seqMulA_2 * b_in_reg[50:34]; // @[multiplier.scala 94:103]
  wire [2:0] _T_18 = seqMulA_2 * seqMulB_3; // @[multiplier.scala 94:103]
  wire [42:0] _GEN_1 = {{17'd0}, _T_7[42:17]}; // @[multiplier.scala 98:225]
  wire [43:0] _T_20 = _GEN_1 + _T_8; // @[multiplier.scala 98:225]
  wire [60:0] _T_22 = {_T_20,_T_7[16:0]}; // @[Cat.scala 30:58]
  wire [42:0] _GEN_2 = {{16'd0}, _T_22[60:34]}; // @[multiplier.scala 98:225]
  wire [43:0] _T_24 = _GEN_2 + _T_9; // @[multiplier.scala 98:225]
  wire [77:0] _T_26 = {_T_24,_T_22[33:0]}; // @[Cat.scala 30:58]
  wire [27:0] _GEN_3 = {{1'd0}, _T_26[77:51]}; // @[multiplier.scala 98:225]
  wire [28:0] _T_28 = _GEN_3 + _T_10; // @[multiplier.scala 98:225]
  wire [79:0] _T_30 = {_T_28,_T_26[50:0]}; // @[Cat.scala 30:58]
  wire [42:0] _GEN_4 = {{17'd0}, _T_11[42:17]}; // @[multiplier.scala 98:225]
  wire [43:0] _T_32 = _GEN_4 + _T_12; // @[multiplier.scala 98:225]
  wire [60:0] _T_34 = {_T_32,_T_11[16:0]}; // @[Cat.scala 30:58]
  wire [42:0] _GEN_5 = {{16'd0}, _T_34[60:34]}; // @[multiplier.scala 98:225]
  wire [43:0] _T_36 = _GEN_5 + _T_13; // @[multiplier.scala 98:225]
  wire [77:0] _T_38 = {_T_36,_T_34[33:0]}; // @[Cat.scala 30:58]
  wire [27:0] _GEN_6 = {{1'd0}, _T_38[77:51]}; // @[multiplier.scala 98:225]
  wire [28:0] _T_40 = _GEN_6 + _T_14; // @[multiplier.scala 98:225]
  wire [79:0] _T_42 = {_T_40,_T_38[50:0]}; // @[Cat.scala 30:58]
  wire [17:0] _GEN_7 = {{17'd0}, _T_15[17]}; // @[multiplier.scala 98:225]
  wire [18:0] _T_44 = _GEN_7 + _T_16; // @[multiplier.scala 98:225]
  wire [35:0] _T_46 = {_T_44,_T_15[16:0]}; // @[Cat.scala 30:58]
  wire [17:0] _GEN_8 = {{16'd0}, _T_46[35:34]}; // @[multiplier.scala 98:225]
  wire [18:0] _T_48 = _GEN_8 + _T_17; // @[multiplier.scala 98:225]
  wire [52:0] _T_50 = {_T_48,_T_46[33:0]}; // @[Cat.scala 30:58]
  wire [2:0] _GEN_9 = {{1'd0}, _T_50[52:51]}; // @[multiplier.scala 98:225]
  wire [3:0] _T_52 = _GEN_9 + _T_18; // @[multiplier.scala 98:225]
  wire [54:0] _T_54 = {_T_52,_T_50[50:0]}; // @[Cat.scala 30:58]
  wire [79:0] _GEN_10 = {{26'd0}, _T_30[79:26]}; // @[multiplier.scala 103:183]
  wire [80:0] _T_56 = _GEN_10 + _T_42; // @[multiplier.scala 103:183]
  wire [106:0] _T_58 = {_T_56,_T_30[25:0]}; // @[Cat.scala 30:58]
  wire [55:0] _T_60 = _T_58[106:52] + _T_54; // @[multiplier.scala 103:183]
  reg [107:0] prod; // @[Reg.scala 15:16]
  assign io_prod_out = prod[105:0]; // @[multiplier.scala 106:15]
  always @(posedge clock) begin
    a_in_reg <= io_a_in; // @[multiplier.scala 18:12]
    b_in_reg <= io_b_in; // @[multiplier.scala 19:12]
    prod <= {_T_60,_T_58[51:0]}; // @[Cat.scala 30:58]
  end
// Register and memory initialization
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_REG_INIT
  _RAND_0 = {2{`RANDOM}};
  a_in_reg = _RAND_0[52:0];
  _RAND_1 = {2{`RANDOM}};
  b_in_reg = _RAND_1[52:0];
  _RAND_2 = {4{`RANDOM}};
  prod = _RAND_2[107:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
endmodule
