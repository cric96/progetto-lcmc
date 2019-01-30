push 0
push 5
push 3
add
push f0
lfp
push 7
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
beq true1
push 0
b false1
true1: 
push 1
false1: 
beq true0
push 0
b false0
true0: 
push 1
false0: 
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
