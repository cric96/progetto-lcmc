push 0
lhp
push first0
lhp
sw
lhp
push 1
add
shp
push rest1
lhp
sw
lhp
push 1
add
shp
lfp
push printList3
lfp
push append4
lfp
push filter5
lfp
push quicksort8
lfp
push inc9
lfp
push dec10

push 2

push 1

push 4

push 3

push 2

push 5
push -1
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
lfp
push -13
lfp
add
lw
push -14
lfp
add
lw
push -15
lfp
add
lw
push -9
lfp
add
lw
push -10
lfp
add
lw
js
push -3
lfp
add
lw
push -4
lfp
add
lw
js
halt

first0:
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

rest1:
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

makeList2:
cfp
lra

push 2
lfp
add
lw
push 1
lfp
add
lw
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
srv
sra
pop
pop
pop
sfp
lrv
lra
js

printList3:
cfp
lra
lfp
push makeList2
push 1
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
beq ifTrue0
lfp
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
print
lfp
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
push -3
lfp
lw
add
lw
push -4
lfp
lw
add
lw
js
push -2
lfp
add
lw
push -3
lfp
add
lw
js
b ifFalse0
ifTrue0: 
push -1
ifFalse0: 
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

append4:
cfp
lra
push 1
lfp
add
lw
push -1
beq equal2
push 0
b equal3
equal2: 
push 1
equal3: 
push 1
beq ifTrue1

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
push 2
lfp
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
push 1
add
lw
js
push -5
lfp
lw
add
lw
push -6
lfp
lw
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
push 9998
lw
lhp
sw
lhp
lhp
push 1
add
shp
b ifFalse1
ifTrue1: 
push 2
lfp
add
lw
ifFalse1: 
srv
sra
pop
pop
pop
sfp
lrv
lra
js

filter5:
cfp
lra
push 1
lfp
add
lw
push -1
beq equal4
push 0
b equal5
equal4: 
push 1
equal5: 
push 1
beq ifTrue2
lfp
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
push 3
lfp
add
lw
push 2
lfp
add
lw
js
push 1
beq ifTrue3
lfp
push 3
lfp
add
lw
push 2
lfp
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
push 1
add
lw
js
push -7
lfp
lw
add
lw
push -8
lfp
lw
add
lw
js
b ifFalse3
ifTrue3: 

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
push 3
lfp
add
lw
push 2
lfp
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
push 1
add
lw
js
push -7
lfp
lw
add
lw
push -8
lfp
lw
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
push 9998
lw
lhp
sw
lhp
lhp
push 1
add
shp
ifFalse3: 
b ifFalse2
ifTrue2: 
push -1
ifFalse2: 
srv
sra
pop
pop
pop
pop
sfp
lrv
lra
js

beforePivot6:
cfp
lra
lfp
push -2
lfp
lw
add
lw
push 1
lfp
add
lw
push 3
lfp
lw
add
lw
push 2
lfp
lw
add
lw
js
srv
sra
pop
pop
sfp
lrv
lra
js

afterPivot7:
cfp
lra
lfp
push -2
lfp
lw
add
lw
push 1
lfp
add
lw
push 3
lfp
lw
add
lw
push 2
lfp
lw
add
lw
js
push 0
beq not0
push 0
b not1
not0: 
push 1
not1: 
srv
sra
pop
pop
sfp
lrv
lra
js

quicksort8:
cfp
lra
push 1
lfp
add
lw
push -1
beq equal6
push 0
b equal7
equal6: 
push 1
equal7: 
push 1
beq ifTrue4
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
b ifFalse4
ifTrue4: 
push 0
ifFalse4: 
lfp
push beforePivot6
lfp
push afterPivot7
push 1
lfp
add
lw
push -1
beq equal8
push 0
b equal9
equal8: 
push 1
equal9: 
push 1
beq ifTrue5
lfp

push -2
lfp
add
lw
lfp
push 3
lfp
add
lw
push 2
lfp
add
lw
lfp
push -5
lfp
add
lw
push -6
lfp
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
push 1
add
lw
js
push -7
lfp
lw
add
lw
push -8
lfp
lw
add
lw
js
push -9
lfp
lw
add
lw
push -10
lfp
lw
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
push 3
lfp
add
lw
push 2
lfp
add
lw
lfp
push -3
lfp
add
lw
push -4
lfp
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
push 1
add
lw
js
push -7
lfp
lw
add
lw
push -8
lfp
lw
add
lw
js
push -9
lfp
lw
add
lw
push -10
lfp
lw
add
lw
js
push -5
lfp
lw
add
lw
push -6
lfp
lw
add
lw
js
b ifFalse5
ifTrue5: 
push -1
ifFalse5: 
srv
pop
pop
pop
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

inc9:
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
bleq lessequal0
push 0 
b lessequal1 
lessequal0: 
push 1 
lessequal1: 
srv
sra
pop
pop
pop
sfp
lrv
lra
js

dec10:
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
