push 0
lhp
push getWeight0
lhp
sw
lhp
push 1
add
shp
push isHeavierThan1
lhp
sw
lhp
push 1
add
shp
push isFunny2
lhp
sw
lhp
push 1
add
shp
e:
push 30
push 1
lhp
sw
lhp
push 1
add
shp
lhp
sw
lhp
push 1
add
shp
push 9998
lw
lhp
sw
lhp
lhp
push 1
add
shp
e:
push 20
push 0
lhp
sw
lhp
push 1
add
shp
lhp
sw
lhp
push 1
add
shp
push 9998
lw
lhp
sw
lhp
lhp
push 1
add
shp
lfp
push -3
lfp
add
lw
push -3
lfp
add
lw
lw
push 2
add
lw
js
print
lfp
push -4
lfp
add
lw
push -4
lfp
add
lw
lw
push 2
add
lw
js
print
lfp
push 10
push -4
lfp
add
lw
push -3
lfp
add
lw
push -3
lfp
add
lw
lw
push 1
add
lw
js
print
halt

getWeight0:
cfp
lra
push -1
lfp
lw
add
lw
srv
sra
pop
sfp
lrv
lra
js

isHeavierThan1:
cfp
lra
push -1
lfp
lw
add
lw
lfp
push 1
lfp
add
lw
push 1
lfp
add
lw
lw
push 0
add
lw
js
push 2
lfp
add
lw
add
sub 
push -1 
bleq greaterequal0
push 1 
b greaterequal1 
greaterequal0: 
push 0 
greaterequal1: 
srv
sra
pop
pop
pop
sfp
lrv
lra
js

isFunny2:
cfp
lra
push -2
lfp
lw
add
lw
srv
sra
pop
sfp
lrv
lra
js
