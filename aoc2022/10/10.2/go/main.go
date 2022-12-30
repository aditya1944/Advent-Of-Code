package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strconv"
	"strings"
)

func main() {

	readFile, err := os.Open("../../input")
	if err != nil {
		log.Fatalf("file could not open, err : %v", err)
	}
	fileScanner := bufio.NewScanner(readFile)

	fileScanner.Split(bufio.ScanLines)

	var (
		matx        = buildMatx()
		registerVal = 1
		cycle       = 0
		crtRowIndex = 0
		crtColIndex = 0
	)

	for fileScanner.Scan() {
		line := fileScanner.Text()
		tokens := strings.Split(line, " ")

		switch tokens[0] {
		case "addx":
			ch := make(chan int)
			go func(ch chan int) {
				draw(crtRowIndex, crtColIndex, registerVal, &matx)
				ch <- 1
			}(ch)

			cycle += 1

			<-ch

			crtColIndex += 1
			if crtColIndex == 40 {
				crtRowIndex += 1
				crtColIndex = 0
			}

			go func(ch chan int) {
				draw(crtRowIndex, crtColIndex, registerVal, &matx)
				ch <- 1
			}(ch)

			cycle += 1

			<-ch

			crtColIndex += 1
			if crtColIndex == 40 {
				crtRowIndex += 1
				crtColIndex = 0
			}

			inputVal, err := strconv.Atoi(tokens[1])
			if err != nil {
				log.Fatalf("error in reading val, err : %v", err)
			}

			registerVal += inputVal

		case "noop":

			ch := make(chan int)
			go func(ch chan int) {
				draw(crtRowIndex, crtColIndex, registerVal, &matx)
				ch <- 1
			}(ch)

			cycle += 1

			<-ch

			crtColIndex += 1
			if crtColIndex == 40 {
				crtRowIndex += 1
				crtColIndex = 0
			}

		}
	}

	for _, row := range matx {
		for _, val := range row {
			fmt.Printf("%q ", val)
		}
		fmt.Println()
	}

}

func draw(crtR, crtC, sprite int, matx *[6][40]byte) {

	if crtC == sprite-1 {
		matx[crtR][crtC] = '#'
	} else if crtC == sprite {
		matx[crtR][crtC] = '#'
	} else if crtC == sprite+1 {
		matx[crtR][crtC] = '#'
	}
}

func buildMatx() [6][40]byte {
	matx := [6][40]byte{}

	for rowIndex, row := range matx {
		for colIndex := range row {
			matx[rowIndex][colIndex] = '.'
		}
	}

	return matx
}
