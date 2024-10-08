package com.example.material_design.ContraintLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstraintAs() {

    ConstraintLayout(
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize()
    ) {
        val (buttonA, buttonB, fill) = createRefs()
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(buttonA) {
                }
        ) {
            Text(text = "Button")

        }
        Button(onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(buttonB) {
                    end.linkTo(parent.start)
                }
        ) {
            Text(text = "Button")
        }

    }
}

@Composable
fun WrappedButton() {
    Button(
        onClick = { /* Handle click */ },
        modifier = Modifier.wrapContentSize()
    ) {
        Text(
            text = "Short",
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun MyConstrainLayout() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var (text1, text2, box) = createRefs()
        Text(
            text = "Hello world ",
            modifier = Modifier.constrainAs(text1) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }

        )
        Box(modifier = Modifier
            .size(200.dp)
            .background(Color.Green)
            .constrainAs(box) {
                top.linkTo(text1.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        Text(
            text = "This is a constraint layout", modifier = Modifier.constrainAs(text2) {
                top.linkTo(box.bottom, margin = 16.dp)
                end.linkTo(parent.end, margin = 16.dp)
            }

        )
    }
}

@Composable
fun AdvancedConstrainLayout() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val (text1, text2, box, guideline, barrier) = createRefs()
        val topGuideline = createGuidelineFromTop(0.3f)
        val textBarrier = createEndBarrier(text1, text2)
        Text(text = "first Text ", modifier = Modifier.constrainAs(text1) {
            top.linkTo(topGuideline)
            start.linkTo(parent.start, margin = 16.dp)
        })
        Text(text = "Second text ", modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .constrainAs(box) {
                top.linkTo(text1.bottom, margin = 16.dp)
                start.linkTo(textBarrier, margin = 16.dp)
                width = Dimension.percent(0.4f)
            })
        val startGuideline = createGuidelineFromStart(0.5f)
        Text(text = "Aligned with the 50% guideline", modifier = Modifier.constrainAs(guideline) {
            top.linkTo(box.bottom, margin = 16.dp)
            start.linkTo(startGuideline)
        })
    }
}

@Composable
fun ConstraintLayoutDemo() {
    ConstraintLayout(
        modifier = Modifier
            .padding(50.dp)
            .size(200.dp)
    ) {
        val (redBox, blueBox, yellowBox, text) = createRefs()
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Red)
            .constrainAs(redBox) {})
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                top.linkTo(redBox.bottom)
                start.linkTo(redBox.end)
            })
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.Yellow)
            .constrainAs(yellowBox) {
                bottom.linkTo(blueBox.bottom)
                start.linkTo(blueBox.end, 20.dp)
            })
        Text(text = "Hello World ", Modifier.constrainAs(text) {
            top.linkTo(parent.top)
            start.linkTo(yellowBox.start)
        })
    }
}

@Composable
fun Flexible() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        // Create references for the boxes
        val (greenBox, blueBox, pinkBox, yellowBox, blackBox) = createRefs()
        // Box A
        Box(
            modifier = Modifier
                .padding(end = 5.dp)
                .size(100.dp)
                .background(Color.Green)
                .constrainAs(greenBox) {}
        ) {
            Text(text = "A", fontSize = 30.sp, modifier = Modifier.align(Alignment.Center))
        }
        //Box B
        Box(modifier = Modifier
            .padding(end = 5.dp)
            .size(100.dp)
            .background(Color.Blue)
            .constrainAs(blueBox) {
                start.linkTo(parent.end)//Align
                start.linkTo(greenBox.end)
            }) {
            Text(
                text = "B",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        //Box C
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Magenta)
            .constrainAs(pinkBox) {
                start.linkTo(blueBox.end)//Align
            }) {
            Text(
                text = "C",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
//        // Box D
//        Box(modifier = Modifier
//            .size(100.dp)
//            .background(Color.Yellow)
//            .constrainAs(yellowBox) {
//                top.linkTo(greenBox.bottom)
//
//            }
//        ) {
//            Text(
//                text = "D", color = Color.Black, fontSize = 30.sp,
//                modifier = Modifier.align(Alignment.Center)
//            )
//        }
//        Box(modifier = Modifier
//            .size(100.dp)
//            .background(Color.Black)
//            .constrainAs(blackBox) {
//                start.linkTo(blueBox.start)
//                end.linkTo(blueBox.end)
//                top.linkTo(blueBox.bottom)
//            })
    }
}

@Composable
fun ConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (button, text, reference) = createRefs()
        Box(modifier = Modifier.constrainAs(reference) {
            //... linkTo
        })
    }
}


//Key Concepts
//createRefs():
//
//Use createRefs() to create references for the composables. This allows you to set constraints relative to these references.
//constrainAs():
//
//This function is used to apply constraints to a composable. You pass in the reference created by createRefs() and define the constraints.
//Constraints:
//
//top.linkTo(): Aligns the top of one composable to the top of another composable or parent.
//bottom.linkTo(): Aligns the bottom of one composable to the bottom of another composable or parent.
//start.linkTo(): Aligns the start (left) of one composable to the start of another composable or parent.
//end.linkTo(): Aligns the end (right) of one composable to the end of another composable or parent.
//centerVerticallyTo(): Centers the composable vertically within another composable or parent.
//centerHorizontallyTo(): Centers the composable horizontally within another composable or parent.
//Modifiers:
//
//Use modifiers such as size(), background(), and padding() to style the composables within the ConstraintLayout.