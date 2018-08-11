<?php
	class clsVehicle{
		//the attributs are private
		// can only be accessed fro inside the class
		private $Model;
		private $Price;
		//Read Only Variables
		private $MarkupPercent; 
		private $TaxRate;
		
		//Create a constructor. only one constructor allowed
		public function __construct($pModel="",$pPrice=0){
			//p stands for parameter m_ stand for member as in attribute
			
			//cannot say $Model to refer to the Model attribute
			// if you do, PHP will create a new variable on the fly and not 
			// Use $this to refer to an attribute. $this is a reference to the current calling object
			$this->setModel($pModel);
			$this->setPrice($pPrice);  //dollar before the 'this', and variable doesn't get $ sign
			$this->setTaxRate(0.15);
			$this->setMarkupPercent(0.98);
			
		}
		public function GetMeData(){
			return ("The " . $this->Model . " is priced at $" . $this->Price . ".00");
		}
		public function getModel(){
			return $this->Model;
		}
		
		public function setModel($pModel){
			// $this->Model is the attribute
			// $pModel is the parameter
			$dataUpdated=true;
			if($pModel!=null)
				$this->Model = $pModel;
			else
				$dataUpdated=false;
			return $dataUpdated;
		}
		
		public function getPrice(){
			return $this->Price;
		}
		private setMarkupPercent($pMarkupPercent=0.45){
			$this->MarkupPercent = $pMarkupPercent;
		}
		private setTaxRate($pTaxRate=0.13){
			$this->MarkupPercent=$pTaxRate;
		}
		public getMarkupPercent(){
			return $this->MarkupPercent;
		}
		public getTaxRate(){
			return $this->TaxRate;
		}
		public function setPrice($pPrice){
			$priceCorrect = true;
			if($pPrice >= 0)
				$this->Price=$pPrice;
			else
				$priceCorrect=false;
			return $priceCorrect;
		}
		public function __destruct(){
			//close db connection
		}
	}
?>
