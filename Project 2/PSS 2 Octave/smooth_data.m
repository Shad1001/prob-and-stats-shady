% smooth_data.m
function y_sm = smooth_data(inFile, windowSize, outFile)
  %SMOOTH_DATA  Moving-average smoothing with shrinking windows at edges
  D = csvread(inFile);
  x = D(:,1);
  y = D(:,2);
  N    = length(y);
  y_sm = zeros(N,1);
  for i = 1:N
    left  = max(1,       i - windowSize);
    right = min(N, i + windowSize);
    y_sm(i) = mean( y(left:right) );
  end
  csvwrite(outFile, [x, y_sm]);
  fprintf("Wrote %s   y-range [%.0f, %.0f]\n",
          outFile, min(y_sm), max(y_sm));
end

