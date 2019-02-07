push 0
lhp
lhp
push m0
lhp
sw
lhp
push 1
add
shp
push c1
lhp
sw
lhp
push 1
add
shp
lhp
push m0
lhp
sw
lhp
push 1
add
shp
push c1
lhp
sw
lhp
push 1
add
shp
lhp
push m0
lhp
sw
lhp
push 1
add
shp
push c1
lhp
sw
lhp
push 1
add
shp
e:
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
print
halt

m0:
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

c1:
cfp
lra
lfp
push 1
lfp
lw
lw
push 0
lfp
lw
lw
add
lw
js
srv
sra
pop
sfp
lrv
lra
js
