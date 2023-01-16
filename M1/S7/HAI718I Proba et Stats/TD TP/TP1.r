n <- 15
5 -> n
x <- 1
X <- 10
n
x
X
n <- 10 + 2
n
n <- 3 + rnorm(1)
(10 + 2)*5
name <- "Carmen"; n1 <- 10; n2 <- 100; m<- 0.5
ls()
ls.str()

M <- data.frame(n1, n2, m)
ls.str(pat = "M")

rm(list = ls())
ls()

help.start()

x11(); x11(); pdf();
dev.list()
dev.cur()
dev.set(3)
dev.cur()
dev.off(2)
dev.list()
dev.off()
dev.off()

x <- rnorm(1000)
y <- rnorm(1000)
plot(x, 3*x+5)
plot(x,y,xlab="Mille valeurs au hasard", ylab="Mille autres valeurs",
     xlim=c(-2,2), ylim=c(-2,2), pch=22, col="red", bg="yellow", bty="l",
     tcl=0.4, main="Configurer les graphiques en R", las=1, cex=1.5)

hist(x,probability = T)
lines(density(x))

#Ex7

P_eg3 <- pbinom(3,18,1/6) - pbinom(2,18,1/6)
P_sup3 <- 1 - pbinom(2,18,1/6)
P_infeg3 <- pbinom(3,18,1/6)
P_infeg16 <- pbinom(16,18,1/6)


#Ex8

pnorm(1.41,0,1)
pnorm(-2.07,0,1)
pnorm(1.41,0,1)
(1 - pnorm(-1.26,0,1))

qnorm(0.95,0,1)
pnorm(0.1,0,1)
pnorm(0.01,0,1)

pnorm(-5,-5,16)
pnorm(0,-5,16)
1 - pnorm(5,-5,16)

qnorm(0.95,-5,4)
pnorm(0.05,-5,16)
pnorm(0.99,-5,16)

#Ex9
pchisq(6.26,15)
pchisq(6.26,10)
1 - pchisq(3.25,15)
1 - pchisq(3.25,10)
1- pchisq(11.52,25)

qchisq(0.01,15)
qchisq(0.05,15)
qchisq(1 - 0.99,15)

#Ex10
pt(0.408,5)
pt(-2.07,5)
pt(-0.132,5)

qt(0.05,5)
- qt(0.9,5)
qt(0.5,5)

#Ex11
hist(rbinom(100,20,0.04))
hist(rbinom(100000,20,0.04))

#Ex12
hist(rnorm(100, -5, 4))
hist(rnorm(100000, -5, 4))

dnorm(0:100,-5,4)
points(dnorm(0:100,-5,4))

#Ex13








