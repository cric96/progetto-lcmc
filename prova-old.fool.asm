push 0
push 5
push 3
add
push f0
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
ifTrue0: 
push 0
ifFalse0: 
print
halt

f0:
cfp
lra
push 1
push -2
lfp
add
lw
push 1
lfp
add
lw
push 2
lfp
add
lw
beq equal2
push 0
b equal3
equal2: 
push 1
equal3: 
beq equal0
push 0
b equal1
equal0: 
push 1
equal1: 
srv
pop
sra
pop
pop
pop
sfp
lrv
lra
js
