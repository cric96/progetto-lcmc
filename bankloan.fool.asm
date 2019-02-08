push 0
lhp
push getMon0
lhp
sw
lhp
push 1
add
shp
lhp
push getMon0
lhp
sw
lhp
push 1
add
shp
push getInv1
lhp
sw
lhp
push 1
add
shp
lhp
push getLoan2
lhp
sw
lhp
push 1
add
shp
push openLoan3
lhp
sw
lhp
push 1
add
shp
lhp
push getLoan2
lhp
sw
lhp
push 1
add
shp
push openLoan4
lhp
sw
lhp
push 1
add
shp


push 50000
push 40000
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
push 9997
lw
lhp
sw
lhp
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
push 9995
lw
lhp
sw
lhp
lhp
push 1
add
shp

push 20000
push 5000
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
push 9997
lw
lhp
sw
lhp
lhp
push 1
add
shp
lfp
push -7
lfp
add
lw
push -6
lfp
add
lw
push -6
lfp
add
lw
lw
push 1
add
lw
js
push -8
lfp
add
lw
push -1
beq equal0
push 0
b equal1
equal0: 
push 1
equal1: 
push 1
beq ifTrue2
lfp
push -8
lfp
add
lw
push -8
lfp
add
lw
lw
push 0
add
lw
js
b ifFalse2
ifTrue2: 
push 0
ifFalse2: 
print
halt

getMon0:
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

getInv1:
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

getLoan2:
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

openLoan3:
cfp
lra
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
push 1
add
lw
js
add
push 30000
sub 
push -1 
bleq greaterequal0
push 1 
b greaterequal1 
greaterequal0: 
push 0 
greaterequal1: 
push 1
beq ifTrue0
push -1
b ifFalse0
ifTrue0: 

lfp
push -1
lfp
lw
add
lw
push -1
lfp
lw
add
lw
lw
push 0
add
lw
js
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
ifFalse0: 
srv
sra
pop
pop
sfp
lrv
lra
js

openLoan4:
cfp
lra
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
push 20000
sub 
push -1 
bleq greaterequal2
push 1 
b greaterequal3 
greaterequal2: 
push 0 
greaterequal3: 
push 1
beq ifTrue1
push -1
b ifFalse1
ifTrue1: 

lfp
push -1
lfp
lw
add
lw
push -1
lfp
lw
add
lw
lw
push 0
add
lw
js
lfp
push -1
lfp
lw
add
lw
push -1
lfp
lw
add
lw
lw
push 1
add
lw
js
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
push 9997
lw
lhp
sw
lhp
lhp
push 1
add
shp
ifFalse1: 
srv
sra
pop
pop
sfp
lrv
lra
js
