require 'sinatra'
require './calculadora'

set :port,4000

get '/soma' do
  cal = Calculadora.new
  a = params["a"].to_i
  b = params["b"].to_i
  result = cal.soma(a,b)
  "Resultado: #{result}"
end

