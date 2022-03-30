
set ns [new Simulator]

set nf [open prog1.nam w]
$ns namtrace-all $nf

set nt [open prog1.tr w]
$ns trace-all $nt

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 600Kb 10ms DropTail

$ns queue-limit $n1 $n2 5

set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 800
$cbr0 set interval_ 0.008
$cbr0 attach-agent $udp0

set sink [new Agent/Null]
$ns attach-agent $n2 $sink
$ns connect $udp0 $sink

proc finish {} {
	global ns nf nt
	$ns flush-trace
	close $nf
	close $nt
	exec nam prog1.nam &
	exit 0
}

$ns at 0.1 "$cbr0 start"
$ns at 5.0 "$cbr0 stop"
$ns at 5.5 "finish"
$ns run
