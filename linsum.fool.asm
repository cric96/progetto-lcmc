push 0
lfp
push g0
lfp
push f2
lfp
push 2
push -4
lfp
add
lw
push -5
lfp
add
lw
js
print
halt

g0:
cfp
lra
lfp
push 7
push 5
push 2
lfp
add
lw
push 1
lfp
add
lw
js
srv
sra
pop
pop
pop
sfp
lrv
lra
js

linsum1:
cfp
lra
push 1
lfp
add
lw
push 2
lfp
add
lw
add
push 1
lfp
lw
add
lw
mult
srv
sra
pop
pop
pop
sfp
lrv
lra
js

f2:
cfp
lra
lfp
push linsum1
lfp
push -2
lfp
add
lw
push -3
lfp
add
lw
push -2
lfp
lw
add
lw
push -3
lfp
lw
add
lw
js
srv
pop
pop
sra
pop
pop
sfp
lrv
lra
js
