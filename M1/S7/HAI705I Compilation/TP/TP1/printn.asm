# Print first n integer starting by 1

.text
main:	li $v0, 5
	syscall
	li $t0, 1
	move $t1, $v0
for:	bgt $t0, $t1, end
	move $a0, $t0
	li $v0, 1
	syscall
	addi $t0, $t0, 1
	j for
end: