set ns [new Simulator]

set tf [open prog2.tr w]
$ns trace-all $tf

set nf [open prog2.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]

$n0 label "Ping1"
$n1 label "Ping2"
$n2 label "Ping3"
$n3 label "Ping4"
$n5 label "Ping5"

$ns duplex-link $n0 $n4 100Mb 300ms DropTail
$ns duplex-link $n1 $n4 1Mb 300ms DropTail
$ns duplex-link $n2 $n4 1Mb 300ms DropTail
$ns duplex-link $n3 $n4 100Mb 300ms DropTail
$ns duplex-link $n4 $n5 1Mb 300ms DropTail

set ping1 [new Agent/Ping]
$ns attach-agent $n0 $ping1
$ping1 set packetSize_ 500000
$ping1 set interval_ 0.0001

set ping2 [new Agent/Ping]
$ns attach-agent $n1 $ping2

set ping3 [new Agent/Ping]
$ns attach-agent $n2 $ping3
$ping3 set packetSize_ 7500
$ping3 set interval_ 0.0001

set ping4 [new Agent/Ping]
$ns attach-agent $n3 $ping4

set ping5 [new Agent/Ping]
$ns attach-agent $n5 $ping5

$ns queue-limit $n0 $n4 5
$ns queue-limit $n2 $n4 3
$ns queue-limit $n4 $n5 2

Agent/Ping instproc recv {from rtt} {
	$self instvar node_
	puts " The node [$node_ id] received an reply from $from with round trip time of $rtt"
}

$ns connect $ping1 $ping5
$ns connect $ping3 $ping4

proc finish {} {
	global ns nf tf
	exec nam prog2.nam &
	$ns flush-trace
	close $tf
	close $nf
	exit 0
}

$ns at 0.1 "$ping1 send"
$ns at 0.2 "$ping1 send"
$ns at 0.3 "$ping1 send"
$ns at 0.4 "$ping1 send"
$ns at 0.5 "$ping1 send"
$ns at 0.6 "$ping1 send"
$ns at 0.7 "$ping1 send"
$ns at 0.8 "$ping1 send"
$ns at 0.9 "$ping1 send"
$ns at 1.0 "$ping1 send"
$ns at 1.0 "$ping1 send"
$ns at 1.1 "$ping1 send"
$ns at 1.2 "$ping1 send"
$ns at 1.3 "$ping1 send"
$ns at 1.4 "$ping1 send"
$ns at 1.5 "$ping1 send"
$ns at 1.6 "$ping1 send"
$ns at 1.7 "$ping1 send"
$ns at 1.8 "$ping1 send"
$ns at 0.1 "$ping3 send"
$ns at 0.2 "$ping3 send"
$ns at 0.3 "$ping3 send"
$ns at 0.4 "$ping3 send"
$ns at 0.5 "$ping3 send"
$ns at 0.6 "$ping3 send"
$ns at 0.7 "$ping3 send"
$ns at 0.8 "$ping3 send"
$ns at 0.9 "$ping3 send"
$ns at 1.0 "$ping3 send"
$ns at 1.1 "$ping3 send"
$ns at 1.2 "$ping3 send"
$ns at 1.3 "$ping3 send"
$ns at 1.4 "$ping3 send"
$ns at 1.5 "$ping3 send"
$ns at 1.6 "$ping3 send"
$ns at 1.7 "$ping3 send"
$ns at 1.8 "$ping3 send"
$ns at 3.0 "finish"
$ns run
