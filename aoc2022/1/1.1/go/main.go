package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
)

func main() {
	f, err := os.Open("../../input")
	if err != nil {
		log.Fatalf("error happened, err: %v", err)
	}
	defer f.Close()
	sc := bufio.NewScanner(f)
	sc.Split(bufio.ScanLines)
	maxCount, currCount := 0, 0
	currNum := 1
	for sc.Scan() {
		line := sc.Text()
		if num, err := strconv.Atoi(line); err == nil {
			currCount += num
		} else {
			if currCount > maxCount {
				maxCount = currCount
			}
			currCount = 0
			currNum += 1
		}
	}
	fmt.Print(maxCount)
}
