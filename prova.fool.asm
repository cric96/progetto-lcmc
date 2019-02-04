push 0
lfp
push f1
lfp
push 3
push 2
push 1
push -2
lfp
add
lw
push -3
lfp
add
lw
js
print
halt

g0:
cfp
lra
push 1
srv
sra
pop
pop
sfp
lrv
lra
js

f1:
cfp
lra
lfp
push g0
lfp
push 1
push -2
lfp
add
lw
push -3
lfp
add
lw
js
srv
pop
pop
sra
pop
pop
pop
pop
sfp
lrv
lra
js
