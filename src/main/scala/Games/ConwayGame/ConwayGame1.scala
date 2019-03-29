package Games.ConwayGame

/**
  * Credit goes to: https://gist.github.com/lbialy/2a485cf6ae85c22ea4e70ebb360f2cb1
  * Extra: coordinates legalizing size: https://rosettacode.org/wiki/Conway%27s_Game_of_Life/Scala
  */
object ConwayGame1 {

	 /**
	   * Game RULES:
	   *
	   * The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells,
	   * each of which is in one of two possible states, alive or dead, (or populated and unpopulated,
	   * respectively). Every cell interacts with its eight neighbours, which are the cells that are
	   * horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions
	   * occur:
	   *
	   * (R1) Any live cell with fewer than two live neighbours dies, as if by underpopulation.
	   * (R2) Any live cell with two or three live neighbours lives on to the next generation.
	   * (R3) Any live cell with more than three live neighbours dies, as if by overpopulation.
	   * (R4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	   *
	   * The initial pattern constitutes the seed of the system. The first generation is created by
	   * applying the above rules simultaneously to every cell in the seed; births and deaths occur
	   * simultaneously, and the discrete moment at which this happens is sometimes called a tick.
	   * Each generation is a pure function of the preceding one. The rules continue to be applied
	   * repeatedly to create further generations.
	   *
	   */

	 /**
	   * PROCEDURE:
	   *
	   * 1. Start with a list of live cells. Each cell is basically just a position, because we know they are
	   * all alive.
	   * 2. Generate the list of all surrounding cells. These are the cells that may come to live due to the
	   * reproduction rule.
	   * 3. Combine the two lists, removing duplicates.
	   * 4. For each cell in this list of candidate cells, determine if it is alive (it was in the list
	   * of live cells) and count its neighbors.
	   * 5. For each cell in the list, apply the rules (based on current state and neighbor count) to
	   * decide if it should live or die.
	   * 6. Filter the list to just the ones that live.
	   *
	   *
	   */

	 type Cell = (Int, Int) //the x - y coordinate on a grid
	 type ConwayState = Set[Cell]

	 def isAlive(p:Cell, s:ConwayState):Boolean = s.contains(p)

	 def isDead(p:Cell, s:ConwayState):Boolean = ! isAlive(p, s)

	 /*def gameOfLife(state: ConwayState): Stream[ConwayState] =
		  if (state.isEmpty) Stream.empty
		  else state #:: gameOfLife(nextGeneration(state))*/

	 def nextGeneration(state: ConwayState): ConwayState = {
		  for {
			   field <- candidateCells(state)
			   result <- updateGeneration(field, state)
		  } yield result
	 }
	 //Applying the rules for each generation: this is one tick or instance or generation when rules are applied
	 def updateGeneration(cell: Cell, state: ConwayState): Option[Cell] = {

		  countNeighbors(cell, state) match {
			   // dead field with 3 living neighbours becomes live cell
			   case x if isDead(cell, state) && x == 3 => Some(cell)
		       // any live cell with 3 or 2 neighbors  lives on to next generation
			   case x if isAlive(cell, state) && x == 2 || x == 3 => Some(cell)
			   case _ => None
		  }
	 }
	 //Also creating the neighbors for a particular cell.
	 def countNeighbors(pos: Cell, state: ConwayState): Int = (neighborCells(pos) - pos).intersect(state).size
	 //TODO or just use candidateCells.size?

	 def candidateCells(state: ConwayState): ConwayState = (state flatMap neighborCells) ++ state

	 //commpare with phelps
	 /*def candidateCells(cells: List[Cell]) = {
		  cells.toSet.flatMap((cell: Cell) => neighbors(cell)) ++ cells
	 }*/

	 //def expand(pos: Position) = (for {
	 //  x <- pos._1 - 1 until pos._1 + 2
	 //  y <- pos._2 - 1 until pos._2 + 2
	 //} yield (x, y)).toSet

	 //was expand()
	 def neighborCells(p: Cell) = Set(
		  (p._1 - 1, p._2 - 1), (p._1, p._2 - 1), (p._1 + 1, p._2 - 1),
		  (p._1 - 1, p._2),          p,           (p._1 + 1, p._2),
		  (p._1 - 1, p._2 + 1), (p._1, p._2 + 1), (p._1 + 1, p._2 + 1)
	 )


	 def main(args: Array[String]) {

		  import org.scalatest.Assertions._

		  val blinker1:ConwayState = Set((2, 1), (2,2), (2,3))
		  val blinker2:ConwayState = Set((1,2), (2,2), (3,2))

		  println(nextGeneration(blinker1))

	 }

	 /*def draw(state: ConwayState) = {
		  val minx = state.foldLeft(Int.MaxValue)((acc, pos) => if (pos._1 < acc) pos._1 else acc)
		  val miny = state.foldLeft(Int.MaxValue)((acc, pos) => if (pos._2 < acc) pos._2 else acc)
		  val maxx = state.foldLeft(Int.MinValue)((acc, pos) => if (pos._1 > acc) pos._1 else acc)
		  val maxy = state.foldLeft(Int.MinValue)((acc, pos) => if (pos._2 > acc) pos._2 else acc)

		  (for {
			   y <- miny - 2 until maxy + 3
			   x <- minx - 2 until maxx + 3
		  } yield (x, y)).foreach(pos => {
			   if (state.contains(pos)) print("#")
			   else print(".")
			   if (pos._1 == maxx + 2) print("\n")
		  })
	 }*/

}
