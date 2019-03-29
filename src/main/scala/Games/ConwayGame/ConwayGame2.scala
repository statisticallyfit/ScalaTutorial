package Games.ConwayGame

/**
  * Credit goes to : https://github.com/chrisphelps/funConway/blob/master/src/test/scala/FunConway.scala
  * * http://chrisphelps.github.io/scala/2013/12/29/A-Scala-Functional-Conways-Game-Of-Life/
  */

import org.scalatest._

object ConwayGame2 {

	 type Cell = (Int, Int) //the x - y coordinate on a grid
	 type ConwayState = Set[Cell]


	 def neighbors(cell: Cell) = cell match {
		  case (x, y) =>
			   for {
					nx <- (x - 1) to (x + 1)
					ny <- (y - 1) to (y + 1)
					if !(nx == x && ny == y)
			   } yield (nx, ny)
	 }

	 def candidateCells(cells: ConwayState) = {
		  cells.toSet.flatMap((cell: Cell) => neighbors(cell)) ++ cells
	 }
	 def isAlive(p:Cell, s:ConwayState):Boolean = s.contains(p)

	 def neighborCount(cell: Cell, state: ConwayState) = {
		  neighbors(cell).filter(c => isAlive(c, state)).size
	 }

	 def augmentCell(cell: Cell, state: ConwayState) = {
		  (cell, neighborCount(cell, state), isAlive(cell, state))
	 }

	 def shouldLive(augmentedCell: (Cell, Int, Boolean)) = {
		  augmentedCell match {
			   case (c, n, true) if n < 2 => false
			   case (c, n, true) if n > 3 => false
			   case (c, n, true) => true
			   case (c, n, false) if n == 3 => true
			   case _ => false
		  }
	 }

	 def evolve(cells: ConwayState) = {
		  val candidates = candidateCells(cells)
		  val augmentedCandidates = candidates.map(c => augmentCell(c, cells))
		  val newgeneration = augmentedCandidates.filter(c => shouldLive(c)).map{ac => ac._1}
		  newgeneration
	 }

	 //Another way to calculate nextconwaystate

	 /*def nextConwayState(state:ConwayState, maxSize:(Int, Int) = (20, 20)):ConwayState = {
		  candidateCells(state).keySet //getting the positions
			   .filter(cell => aliveNextTurn(cell, state)) //getting only the survivor cells
			   .map(cell => (cell, true)) //converting to Set[(Position, Boolean)]
			   .toMap
	 }*/

	//note: or with foldLeft

	 /*val emptyMap: mutable.Map[Position, Boolean] = mutable.Map.empty

	 // accumulating the cells into the mutable Map, then converting it into immutable Map.
	 optionResults.foldLeft(emptyMap)((accMap, pos) => pos match {
		  case Some(cell) => accMap + (cell -> true)
		  case None => accMap
	 }).toMap*/

}


class ConwayGame2Tests extends FlatSpec {

	 import ConwayGame2._

	 "Conway game" should "generate neighbors for a cell" in {
		  val initialCell = (1,1)
		  val expectedCells = List((0,0),(0,1),(0,2),(1,0),(1,2),(2,0),(2,1),(2,2))
		  assert(neighbors(initialCell).toSet === expectedCells.toSet)

	 }

	 it should "generate a list of all potential cells" in {
		  val initialCells = Set((1,1), (1,2))
		  val expectedCells = Set((0,0),(0,1),(0,2),(0,3),
			   (1,0),(1,1),(1,2),(1,3),
			   (2,0),(2,1),(2,2), (2,3))
		  assert(candidateCells(initialCells).toSet === expectedCells.toSet)
	 }

	 it should "count live neighbors" in {
		  val liveCells = Set((1,1), (1,2))
		  assert(neighborCount((0,1), liveCells) === 2)
		  assert(neighborCount((1,1), liveCells) === 1)
		  assert(neighborCount((5,5), liveCells) === 0)
	 }

	 it should "augment cells with count and liveness" in {
		  val liveCells = Set((1,1), (1,2))
		  assert(augmentCell((0,1), liveCells) === ((0,1),2,false))
		  assert(augmentCell((1,1), liveCells) === ((1,1),1,true))
		  assert(augmentCell((5,5), liveCells) === ((5,5),0,false))
	 }

	 it should "kill underpopulated cells" in {
		  val cell = ((1,1), 1, true)
		  assert(shouldLive(cell) === false)
	 }

	 it should "kill overpopulated cells" in {
		  val cell = ((1,1), 4, true)
		  assert(shouldLive(cell) === false)
	 }

	 it should "allow normal cells to live" in {
		  val cell = ((1,1), 2, true)
		  assert(shouldLive(cell) === true)
	 }

	 it should "reproduce cells" in {
		  val cell = ((1,1), 3, false)
		  assert(shouldLive(cell) === true)
	 }

	 it should "maintain the block" in {
		  val liveCells = Set((1,1), (1,2), (2,1), (2,2))
		  assert(evolve(liveCells) === liveCells)
	 }

	 it should "blink the blinker" in {
		  val liveCells = Set((1,0), (1,1), (1,2))
		  val expectedCells = Set((0,1), (1,1), (2,1))
		  assert(evolve(liveCells) === expectedCells)

		  //-------
		  val blinker1:ConwayState = Map((2, 1) -> true, (2, 2) -> true, (2, 3) -> true).keySet
		  val blinker2:ConwayState = Map((1, 2) -> true, (2, 2) -> true, (3, 2) -> true).keySet
		  assert(evolve(blinker1) === blinker2)
		  if(evolve(blinker1) === blinker2 && evolve(blinker2) === blinker1){
			   println("YAY")
		  }
		  assert(evolve(blinker2) === blinker1)
	 }

	 it should "glide the glider" in {
		  val liveCells = Set((0,2),(1,0),(1,2),(2,1),(2,2))
		  val expectedCells = Set((0,2),(1,3),(2,1),(2,2),(2,3))
		  assert(evolve(evolve(liveCells)) === expectedCells)
	 }
}