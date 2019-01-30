push 0
push 5
push 2
add
push veryFunctionPedrolix0
lfp
push 8
push -2
lfp
add 
lw 
lfp
push -3
lfp
add 
lw 
js 
push 1
beq ifTrue0
push 10
b ifFalse0
ifTrue0: push 0
ifFalse0: print
halt

veryFunctionPedrolix0: 
cfp 
lra 
push 2
lfp
add 
lw 
push -2
lfp
add 
lw 
push -2
lfp
lw
add 
lw 
beq true0
push 0
b false0
true0: push 1
false0: srv 
pop
sra 
pop 
pop
pop
sfp 
lrv 
lra 
js 
